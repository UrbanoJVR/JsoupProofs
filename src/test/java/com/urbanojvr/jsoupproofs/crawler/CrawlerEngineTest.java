package com.urbanojvr.jsoupproofs.crawler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

public class CrawlerEngineTest {

    private static final String URL = "https://www.amazon.es/s?k=ssd&__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&ref=nb_sb_noss_1";
    private CrawlerEngine sut;

    @Before
    public void init() throws IOException {
        //sut = new CrawlerEngine(URL);
        String baseUri = "https://www.amazon.es";
        String rootUri = "https://www.amazon.es/s?k=ssd&__mk_es_ES=%C3%85M%C3%85%C5%BD%C3%95%C3%91&ref=nb_sb_noss_1";
        sut = new CrawlerEngine(baseUri, rootUri);
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