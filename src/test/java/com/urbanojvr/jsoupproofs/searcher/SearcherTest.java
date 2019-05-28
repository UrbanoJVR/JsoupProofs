package com.urbanojvr.jsoupproofs.searcher;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearcherTest {

    private Searcher sut;

    @Ignore
    @Test
    public void search_text_in_list() throws IOException {
        String fileName = "filename";
        sut = new SearchEngine();
        sut.searchText(fileName, "united states");
    }

    @Test
    public void search_text_in_proudcts_list() throws IOException {
        String filename = "src/main/resources/links_ecom.json";
        sut = new ProductSearcher();

        ArrayList<String> results = sut.searchText(filename, "united states");


        for(String link : results){
            System.out.println(link);
        }
        System.out.println("SIZE ::::: " + results.size());
    }
}