package com.urbanojvr.jsoupproofs.searcher;

import com.urbanojvr.jsoupproofs.crawler.Crawler;
import com.urbanojvr.jsoupproofs.crawler.CrawlerEngine;
import com.urbanojvr.jsoupproofs.filemanager.JsonSerializer;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearchEngineTest {

    private static final String URI = "http://books.toscrape.com/";
    private static final String TARGET = "united states";
    private static final String FILE_TO_READ = "src/main/resources/links.json";
    private static final String FILE_TO_WRITE = "src/test/resources/matches.json";

    private CrawlerEngine crawler;
    private SearchEngine sut;

    @Before
    public void init() throws IOException {
        sut = new SearchEngine();
        crawler = new CrawlerEngine(URI);
    }

    @Ignore
    @Test
    public void crawl_and_search() throws IOException {
        crawler.crawl();
        ArrayList<String> result = sut.search(crawler.getJsonFileName(), TARGET);
    }

    @Ignore
    @Test
    public void only_search() throws IOException {
        ArrayList<String> result = sut.search(crawler.getJsonFileName(), TARGET);
        for(String l : result){
            System.out.println(l);
        }
    }
}