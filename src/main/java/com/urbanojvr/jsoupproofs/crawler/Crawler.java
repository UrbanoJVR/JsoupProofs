package com.urbanojvr.jsoupproofs.crawler;

import java.io.IOException;
import java.util.ArrayList;

public interface Crawler {
    void crawl() throws IOException;

    void crawl(String url) throws IOException;

    boolean linksEmpty();

    ArrayList<String> getLinksList();
}
