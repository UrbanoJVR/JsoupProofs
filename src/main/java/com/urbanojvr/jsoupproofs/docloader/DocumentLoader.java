package com.urbanojvr.jsoupproofs.docloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

public class DocumentLoader {

    private Document doc;

    public DocumentLoader(){
        this.doc = null;
    }

    public DocumentLoader(String url) throws IOException {
        this.doc = Jsoup.connect(url).get();
    }

    public DocumentLoader(File fileToLoad, String charset) throws IOException {
        this.doc = Jsoup.parse(fileToLoad, charset);
    }

    public Element getBody(){
        return doc.body();
    }

    public Element getHead(){
        return doc.head();
    }

    public Document getDoc(){
        return doc;
    }

    public String getTitle(){
        return doc.title();
    }
}
