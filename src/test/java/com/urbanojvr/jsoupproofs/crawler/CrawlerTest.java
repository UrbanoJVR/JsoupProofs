package com.urbanojvr.jsoupproofs.crawler;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class CrawlerTest {

    private static final String URL = "http://elfreneticoinformatico.com";
    private Crawler crawler;

    @Ignore
    @Test
    public void default_crawling_recursive() throws IOException {
        crawler = new CrawlerEngine(URL);

        crawler.crawl();

        ArrayList<String> links = crawler.getScrapedLinks();
    }

    @Ignore
    @Test
    public void sliced_crawling() throws IOException {
        crawler = new SlicedCrawlerEngine(URL);

        crawler.crawl();

        ArrayList<String> links = crawler.getScrapedLinks();
    }

    @Ignore
    @Test
    public void crawling_with_google_pattern() throws IOException {
        String google = "https://google.com/mySearch";
        String urlTag = "cite.iUh30";
        String nextPageTag ="a#pnnext";
        int pageLimit = 5;

        crawler = new GooglePatternEngine(google, urlTag, nextPageTag, pageLimit);

        crawler.crawl();

        ArrayList<String> links = crawler.getScrapedLinks();
    }

    @Ignore
    @Test
    public void crawling_ecommerce() throws IOException {
        String url = "http://books.toscrape.com/";
        String productContainerTag = "article.product_pod";
        String priceTag = "p.price_color";
        String titleTag = "a[title]";
        String urlTag = "a[href]";
        int pageLimit = 60;
        String nextPageTag = "li.next";

        crawler = new EcommercePatternEngine(url,
                productContainerTag,
                priceTag,
                titleTag,
                urlTag,
                pageLimit,
                nextPageTag);

        crawler.crawl();

        ArrayList<String> links = crawler.getScrapedLinks();
    }
}