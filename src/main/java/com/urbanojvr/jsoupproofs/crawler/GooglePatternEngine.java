package com.urbanojvr.jsoupproofs.crawler;

import com.urbanojvr.jsoupproofs.docloader.DocumentLoader;
import com.urbanojvr.jsoupproofs.filemanager.JsonSerializer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class GooglePatternEngine implements Crawler{

    private static final int FIRST_PAGE_NUMBER = 1;
    private static final String DEAFULT_JSON_FILE_NAME = "src/main/resources/links_google.json";

    private ArrayList<String> scrapedLinks;
    private String actualPage;
    private String resultTag;
    private String nextPageTag;
    private int pageLimit;
    private String jsonFileName;

    public GooglePatternEngine(String parentUrl, String resultTag, String nextPageTag, int pageLimit){
        this.actualPage = parentUrl;
        this.resultTag = resultTag;
        this.nextPageTag = nextPageTag;
        this.scrapedLinks = new ArrayList<>();
        this.pageLimit = pageLimit;
        jsonFileName = DEAFULT_JSON_FILE_NAME;
    }

    @Override
    public void crawl() throws IOException {
        parsePage(actualPage, FIRST_PAGE_NUMBER);
        saveLinksList();
    }

    public void parsePage(String pageUrl, int pageNum) throws IOException {
        System.out.println("PAGE :: " + pageNum);
        Document document = new DocumentLoader(pageUrl).getDoc();
        Elements elements = document.select(resultTag);
        for(Element element : elements){
            scrapedLinks.add(element.text());
            System.out.println(element.text());
        }
        if(pageNum < pageLimit && !document.select(nextPageTag).attr("abs:href").isEmpty()){
            pageNum ++;
            parsePage(document.select(nextPageTag).attr("abs:href"), pageNum);
        } else{
            System.out.println("Finished");
        }
    }

    @Override
    public ArrayList<String> getScrapedLinks() {
        return scrapedLinks;
    }

    private void saveLinksList() throws IOException {
        JsonSerializer serializer = new JsonSerializer();
        serializer.writeFile(scrapedLinks, jsonFileName);
    }

    public void setJsonFileName(String fileName){
        jsonFileName = fileName;
    }
}
