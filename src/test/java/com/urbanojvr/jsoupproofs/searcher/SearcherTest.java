package com.urbanojvr.jsoupproofs.searcher;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SearcherTest {

    private Searcher sut;

    @Test
    public void search_text_in_list() throws IOException {
        String fileName = "filename";
        sut = new SearchEngine();
        sut.searchText(fileName, "united states");
    }

    @Test
    public void search_text_in_proudcts_list() throws IOException {
        String filename = "filename2";
        sut = new ProductSearcher();
        sut.searchText(filename, "united states");
    }
}