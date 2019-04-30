package com.urbanojvr.jsoupproofs.dataextractor;

import com.urbanojvr.jsoupproofs.docloader.DocumentLoader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class SintaxEngine {

    private Document doc;
    private String charset;

    public SintaxEngine(String URL) throws IOException {
        this.doc = new DocumentLoader(URL).getDoc();
    }

    public SintaxEngine(File file) throws IOException {
        charset = "UTF-8";
        this.doc = new DocumentLoader(file, charset).getDoc();
    }

    public Elements getAWithRef(){
        return doc.select("a[href]"); // a with href
    }

    public Elements getImages(String format){
        return doc.select("img[src$=." + format + "]");
    }

    public Elements searchElementAndClass(String elementTag, String elementClass){
        String query = elementTag + "." + elementClass;
        return doc.select(query);
    }
}
