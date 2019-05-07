package com.urbanojvr.jsoupproofs.crawler;

import com.urbanojvr.jsoupproofs.dataextractor.SintaxEngine;
import com.urbanojvr.jsoupproofs.filemanager.JsonSerializer;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Random;

public class SlicedCrawlerEngine extends CrawlerEngine{

    public SlicedCrawlerEngine(String baseURI) throws IOException {
        super(baseURI);
    }

    public SlicedCrawlerEngine(String baseURI, String rootURI) throws IOException {
        super(baseURI, rootURI);
    }

    @Override
    public void crawl(String url){
        try{
            System.out.println("SlicedCrawlerEngine");
            SintaxEngine sintaxEngine = new SintaxEngine(url);
            Elements links = sintaxEngine.getAWithRef();

            for(Element link : links){
                String actualUrl = link.attr("abs:href");

                if(!super.linksList.contains(actualUrl) & actualUrl.startsWith(super.baseURI)){
                    super.print(" * a: <%s>  (%s)", actualUrl, super.trim(link.text(), 35));
                    linksList.add(actualUrl);
                    if((linksList.size() % 100) == 0){
                        JsonSerializer serializer = new JsonSerializer(linksList);
                        serializer.writeFile("links.json");
                    }
                    Thread.sleep(randomTime());
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

    private int randomTime(){
        Random random = new Random();
        return (random.nextInt(5) + 1) * 1000;
    }
}
