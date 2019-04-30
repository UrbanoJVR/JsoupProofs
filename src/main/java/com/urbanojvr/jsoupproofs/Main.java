package com.urbanojvr.jsoupproofs;

import com.urbanojvr.jsoupproofs.dataextractor.DomEngine;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "http://books.toscrape.com/";
        DomEngine domEngine = new DomEngine(url);

        ArrayList<Element> elements = domEngine.getElementsByClass("side_categories");

        System.out.println("Total elements :: " + elements.size());

        elements = domEngine.getLiFromListElement(elements.get(0));
        for(Element element : elements){
            System.out.println(element.text());
        }

        System.out.println("Total elements ::" + elements.size());
    }
}
