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

public class CrawlerEngine {

    private String baseURI;
    private String rootURI;
    private ArrayList<String> linksList;
    private Document doc;

    public CrawlerEngine(String baseURI) throws IOException {
        this.baseURI = baseURI;
        this.rootURI = String.valueOf(baseURI);
        linksList = new ArrayList<String>();
        doc = new DocumentLoader(baseURI).getDoc();
    }

    public CrawlerEngine(String baseURI, String rootURI) throws IOException {
        this.baseURI = baseURI;
        this.rootURI = rootURI;
        linksList = new ArrayList<String>();
        doc = new DocumentLoader(rootURI).getDoc();
    }

    public void crawl() throws IOException {
        crawl(rootURI);
    }

    public void crawl(String url) throws IOException {
        try{
            SintaxEngine sintaxEngine = new SintaxEngine(url);
            Elements links = sintaxEngine.getAWithRef();

            for(Element link : links){
                String actualUrl = link.attr("abs:href");

                if(!linksList.contains(actualUrl) & actualUrl.startsWith(baseURI)){
                    print(" * a: <%s>  (%s)", actualUrl, trim(link.text(), 35));
                    linksList.add(actualUrl);
                    if((linksList.size() % 100) == 0){
                        JsonSerializer serializer = new JsonSerializer(linksList);
                        serializer.writeFile("links.json");
                    }
                    crawl(actualUrl);
                }
            }
        } catch (HttpStatusException statusExcp){
            System.err.println(statusExcp.getMessage() + ". STATUS :: " + statusExcp.getStatusCode());
            System.err.println(statusExcp.getUrl() + " [" + linksList.size() + "]");
        } catch (SocketTimeoutException timeOutExcp){
            System.out.println(timeOutExcp.getMessage());
        } catch (Exception error){
            System.out.println(error.getMessage());
        } catch (StackOverflowError stackOverflowError){
            System.err.println(stackOverflowError.toString());
            System.err.println("[" + linksList.size() + "]");
        }
    }

    public boolean linksEmpty(){
        return linksList.isEmpty();
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }

    public ArrayList<String> getLinksList(){
        return linksList;
    }
}
