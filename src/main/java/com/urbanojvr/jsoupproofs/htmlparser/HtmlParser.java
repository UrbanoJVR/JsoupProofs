package com.urbanojvr.jsoupproofs.htmlparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HtmlParser {

    private Document doc;

    public HtmlParser(){
        this.doc = null;
    }

    public  HtmlParser(String strToParse){
        this.doc = Jsoup.parse(strToParse);
    }

    public  HtmlParser(String strToParse, String baseURL){
        this.doc = Jsoup.parse(strToParse, baseURL);
    }

    public void parseBody(String html){
        this.doc = Jsoup.parseBodyFragment(html);
    }

    public Element getBody(){
        return doc.body();
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
}
