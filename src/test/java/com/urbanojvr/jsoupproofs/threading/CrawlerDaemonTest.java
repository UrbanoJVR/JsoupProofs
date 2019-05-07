package com.urbanojvr.jsoupproofs.threading;

import com.urbanojvr.jsoupproofs.crawler.Crawler;
import com.urbanojvr.jsoupproofs.crawler.CrawlerEngine;
import com.urbanojvr.jsoupproofs.crawler.SlicedCrawlerEngine;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CrawlerDaemonTest {


    private static final String EL_PAIS = "https://www.elpais.com";
    private static final String EL_MUNDO = "https://www.elmundo.es";
    private CrawlerDaemon sut1;
    private CrawlerDaemon sut2;

    @Before
    public void init() throws IOException {
        CrawlerEngine crawlerEngine = new CrawlerEngine(EL_MUNDO);
        SlicedCrawlerEngine slicedCrawlerEngine = new SlicedCrawlerEngine(EL_PAIS);
        sut1 = new CrawlerDaemon(crawlerEngine);
        sut2 = new CrawlerDaemon(slicedCrawlerEngine);
    }

    @Test
    public void runningTwoCrawlers() {
        sut1.start();
        //sut2.start();
    }
}