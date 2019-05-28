package com.urbanojvr.jsoupproofs.crawler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class SlicedCrawlerEngineTest {

    private static final String URL = "https://www.elmundo.es";
    private SlicedCrawlerEngine sut;

    @Before
    public void init() throws IOException {
        sut = new SlicedCrawlerEngine(URL);
    }

    @Ignore
    @Test
    public void infiniteSlicedCrawl() throws IOException {
        Date start = new Date();
        sut.crawl();
        Date finish = new Date();
        long mils = finish.getTime() - start.getTime();
        long seconds = mils / 1000;
        System.out.println("Total URLs crawled at " + URL + " :: " + sut.getScrapedLinks().size());
        System.out.println("Time :: " + seconds);
    }
}