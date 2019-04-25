package com.urbanojvr.jsoupproofs.dataextractor;

import com.urbanojvr.jsoupproofs.docloader.DocumentLoader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DomEngine {

    private Document doc;
    private String charset;

    public DomEngine(String url) throws IOException {
        this.doc = new DocumentLoader(url).getDoc();
    }

    public DomEngine(File file) throws IOException {
        charset = "UTF-8";
        this.doc = new DocumentLoader(file,charset).getDoc();
    }

    public ArrayList<String> getElementTextByClass(String className){
        Elements elements = doc.getElementsByClass(className);
        ArrayList<String> pricesList = new ArrayList<String>();
        for (Element element : elements) {
            pricesList.add(element.text());
        }
        return pricesList;
    }

    public ArrayList<Element> getElementsByClass(String className){
        Elements elements = doc.getElementsByClass(className);
        ArrayList<Element> list = new ArrayList<Element>();
        for(Element element : elements){
            list.add(element);
        }
        return list;
    }

    public ArrayList<Element> getLiFromListElement(Element listElement){
        Elements elements = listElement.getElementsByTag("li");
        ArrayList<Element> list = new ArrayList<Element>();
        for(Element element : elements){
            list.add(element);
        }
        return list;
    }

    public Document getDoc(){
        return doc;
    }

    public void setDoc(Document doc){
        this.doc = doc;
    }
}
