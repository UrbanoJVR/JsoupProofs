package com.urbanojvr.jsoupproofs.crawler;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class GooglePatternEngineTest {

    private static final String PARENT_URI = "https://www.google.com/search?client=ubuntu&channel=fs&q=jsoup&ie=utf-8&oe=utf-8";
    private static final String TAG = "cite.iUh30";
    private static final String NEXT_PAGE_TAG ="a#pnnext";
    private static final int PAGE_LIMIT = 5;
    private GooglePatternEngine sut;

    @Before
    public void init(){
        sut = new GooglePatternEngine(PARENT_URI, TAG, NEXT_PAGE_TAG, PAGE_LIMIT);
    }

    @Test
    public void crawl() throws IOException {
        sut.crawl();
    }
}