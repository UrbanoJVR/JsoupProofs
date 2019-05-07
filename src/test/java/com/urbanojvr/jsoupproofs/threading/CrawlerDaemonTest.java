package com.urbanojvr.jsoupproofs.threading;

import com.urbanojvr.jsoupproofs.crawler.Crawler;
import com.urbanojvr.jsoupproofs.crawler.CrawlerEngine;
import com.urbanojvr.jsoupproofs.crawler.SlicedCrawlerEngine;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class CrawlerDaemonTest {


    private static final String EL_PAIS = "https://www.elpais.com";
    private static final String EL_MUNDO = "https://www.elmundo.es";
    private CrawlerEngine crawlerEngine;
    private SlicedCrawlerEngine slicedCrawlerEngine;
    private CrawlerDaemon daemon;
    private CrawlerDaemon sut2;

    @Before
    public void init() throws IOException {
        daemon = new CrawlerDaemon(EL_MUNDO);
    }

    @Test
    public void runningTwoCrawlers() throws IOException {
        Thread t = new Thread(daemon);
        t.start();
    }
}