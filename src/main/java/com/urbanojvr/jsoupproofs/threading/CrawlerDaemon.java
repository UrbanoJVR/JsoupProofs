package com.urbanojvr.jsoupproofs.threading;

import com.urbanojvr.jsoupproofs.crawler.Crawler;

import java.io.IOException;

public class CrawlerDaemon extends Thread {

    private Crawler crawler;

    public CrawlerDaemon(Crawler crawler){
        this.crawler = crawler;
    }

    @Override
    public void run() {
        try {
            System.out.println("Daemon started");
            crawler.crawl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCrawler(Crawler crawler){
        this.crawler = crawler;
    }
}
