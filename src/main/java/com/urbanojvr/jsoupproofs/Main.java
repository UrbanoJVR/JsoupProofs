package com.urbanojvr.jsoupproofs;

import com.urbanojvr.jsoupproofs.htmlparser.HtmlParser;
import org.jsoup.nodes.Document;

public class Main {
    public static void main(String[] args){
        String text = "<html><head><title>Proof of concept</title></head>"
                + "<body><p>Parsing String into a HTML document</p>" +
                "<p>JSOUP PROOF OFO CONCEPT</p></body></html>";

        HtmlParser parser = new HtmlParser(text);
        Document doc = parser.getDoc();
        System.out.println(doc.body());
    }
}
