package com.urbanojvr.jsoupproofs.crawler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class GooglePatternEngineTest {

    private static final String PARENT_URI = "https://www.google.com/search?client=ubuntu&channel=fs&q=jsoup&ie=utf-8&oe=utf-8";
    private static final String RESULT_TAG = "cite.iUh30";
    private static final String NEXT_PAGE_TAG ="a#pnnext";
    private static final int PAGE_LIMIT = 5;
    private GooglePatternEngine sut;

    @Before
    public void init(){
        sut = new GooglePatternEngine(PARENT_URI, RESULT_TAG, NEXT_PAGE_TAG, PAGE_LIMIT);
    }

    @Ignore
    @Test
    public void crawl() throws IOException {
        sut.crawl();
    }
}