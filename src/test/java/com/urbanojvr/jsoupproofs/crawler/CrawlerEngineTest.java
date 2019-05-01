package com.urbanojvr.jsoupproofs.crawler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

public class CrawlerEngineTest {

    private static final String URL = "https://www.elmundo.es/";
    private CrawlerEngine sut;

    @Before
    public void init() throws IOException {
        sut = new CrawlerEngine(URL);
    }

    @Ignore
    @Test
    public void infiniteCrawl() throws IOException {
        Date start = new Date();
        sut.crawl();
        Date finish = new Date();
        long mils = finish.getTime() - start.getTime();
        long seconds = mils / 1000;
        System.out.println("Total URLs crawled at " + URL + " :: " + sut.getLinksList().size());
        System.out.println("Time :: " + seconds);
    }
}