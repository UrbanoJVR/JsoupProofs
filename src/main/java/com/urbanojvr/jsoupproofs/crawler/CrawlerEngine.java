package com.urbanojvr.jsoupproofs.crawler;

import com.urbanojvr.jsoupproofs.dataextractor.SintaxEngine;
import com.urbanojvr.jsoupproofs.docloader.DocumentLoader;
import com.urbanojvr.jsoupproofs.filemanager.JsonSerializer;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class CrawlerEngine implements Crawler {

    private static final String DEFAULT_JSON_FILE_NAME = "src/main/resources/links.json";
    private static final int DEFAULT_LIST_SIZE = 100;

    private String jsonFileName;
    protected String baseURI;
    private String rootURI;
    ArrayList<String> scrapedLinks;
    private Document doc;
    private int listSize;

    public CrawlerEngine(String baseURI) throws IOException {
        this.baseURI = baseURI;
        this.rootURI = String.valueOf(baseURI);
        scrapedLinks = new ArrayList<String>();
        doc = new DocumentLoader(baseURI).getDoc();
        listSize = DEFAULT_LIST_SIZE;
        jsonFileName = DEFAULT_JSON_FILE_NAME;
    }

    public CrawlerEngine(String baseURI, String rootURI) throws IOException {
        this.baseURI = baseURI;
        this.rootURI = rootURI;
        scrapedLinks = new ArrayList<String>();
        doc = new DocumentLoader(rootURI).getDoc();
        listSize = DEFAULT_LIST_SIZE;
        jsonFileName = DEFAULT_JSON_FILE_NAME;
    }

    public CrawlerEngine(String baseURI, String rootURI, int listSize) throws IOException {
        this.baseURI = baseURI;
        this.rootURI = rootURI;
        scrapedLinks = new ArrayList<String>();
        doc = new DocumentLoader(rootURI).getDoc();
        this.listSize = listSize;
        jsonFileName = DEFAULT_JSON_FILE_NAME;
    }

    @Override
    public void crawl() throws IOException {
        crawl(rootURI);
        saveLinksList();
    }

    public void crawl(String url) throws IOException {
        try{
            SintaxEngine sintaxEngine = new SintaxEngine(url);
            Elements links = sintaxEngine.getAWithRef();

            for(Element link : links){
                String actualUrl = link.attr("abs:href");

                if(!scrapedLinks.contains(actualUrl) && actualUrl.startsWith(baseURI) && scrapedLinks.size() < listSize){
                    print(" * a: <%s>  (%s)", actualUrl, trim(link.text(), 35));
                    scrapedLinks.add(actualUrl);
                    if((scrapedLinks.size() % 100) == 0){
                        saveLinksList();
                        System.out.println(scrapedLinks.size());
                    }
                    crawl(actualUrl);
                }
            }
        } catch (HttpStatusException statusExcp){
            System.err.println(statusExcp.getMessage() + ". STATUS :: " + statusExcp.getStatusCode());
            System.err.println(statusExcp.getUrl() + " [" + scrapedLinks.size() + "]");
        } catch (SocketTimeoutException timeOutExcp){
            System.out.println(timeOutExcp.getMessage());
        } catch (Exception error){
            System.out.println(error.getMessage());
        } catch (StackOverflowError stackOverflowError){
            // System.err.println(stackOverflowError.toString());
            System.err.println("[" + scrapedLinks.size() + "]");
        }
    }

    public boolean linksEmpty(){
        return scrapedLinks.isEmpty();
    }

    protected void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    protected String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    @Override
    public ArrayList<String> getScrapedLinks(){
        return scrapedLinks;
    }

    private void saveLinksList() throws IOException {
        JsonSerializer serializer = new JsonSerializer();
        serializer.writeFile(scrapedLinks,jsonFileName);
    }

    public String getJsonFileName() {
        return jsonFileName;
    }

    public void setJsonFileName(String jsonFileName) {
        this.jsonFileName = jsonFileName;
    }

    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public String getRootURI() {
        return rootURI;
    }

    public void setRootURI(String rootURI) {
        this.rootURI = rootURI;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
}
