package com.urbanojvr.jsoupproofs.docloader;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DocumentLoaderTest {

    private static final String URL = "http://toscrape.com/";
    private static final String TITLE = "Scraping Sandbox";
    private ClassLoader classLoader = getClass().getClassLoader();
    private File fileToLoad = new File(classLoader.getResource("toscrape.html").getFile());
    private DocumentLoader sutFromURL;
    private DocumentLoader sutFromLocalFile;

    @Before
    public void before() throws IOException {
        sutFromURL = new DocumentLoader(URL);
        sutFromLocalFile = new DocumentLoader(fileToLoad, "UTF-8");
    }

    @Test
    public void should_return_correct_title() {
        assertThat(TITLE, equalTo(sutFromURL.getTitle()));
        assertThat(TITLE, equalTo(sutFromLocalFile.getTitle()));
    }

    @Test
    public void should_return_not_empty_body() {
        assertFalse(sutFromURL.getBody().text().isEmpty());
        assertFalse(sutFromLocalFile.getBody().text().isEmpty());
    }

    @Test
    public void head_should_not_be_empty_and_only_contains_the_title() {
        assertFalse(sutFromURL.getHead().text().isEmpty());
        assertThat(TITLE, equalTo(sutFromURL.getHead().text()));
        assertFalse(sutFromLocalFile.getHead().text().isEmpty());
        assertThat(TITLE, equalTo(sutFromLocalFile.getHead().text()));
    }

    @Test
    public void first_h2_element_on_body_should_be_expected_word() {
        String expectedWord = "Books";

        assertThat(expectedWord, equalTo(sutFromURL.getBody().select("h2").get(0).text()));
        assertThat(expectedWord, equalTo(sutFromLocalFile.getBody().select("h2").get(0).text()));
    }
}