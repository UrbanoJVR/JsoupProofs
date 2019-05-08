package com.urbanojvr.jsoupproofs.crawler;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class EcommercePatternEngineTest {

    private static final String URL = "http://books.toscrape.com/";
    private static final String PRODUCT_CONTAINER_TAG = "article.product_pod";
    private static final String PRICE_TAG = "p.price_color";
    private static final String TITLE_TAG = "a[title]";
    private static final String URL_TAG = "a[href]";
    private static final int PAGE_LIMIT = 60;
    private static final String NEXT_PAGE_TAG = "li.next";
    private EcommercePatternEngine sut;

    @Before
    public void init(){
        sut = new EcommercePatternEngine(URL, PRODUCT_CONTAINER_TAG, PRICE_TAG, TITLE_TAG, URL_TAG, PAGE_LIMIT, NEXT_PAGE_TAG);
    }

    @Ignore
    @Test
    public void crawl() throws IOException {
        sut.crawl();
    }
}