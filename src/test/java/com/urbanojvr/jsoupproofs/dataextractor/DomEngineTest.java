package com.urbanojvr.jsoupproofs.dataextractor;

import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DomEngineTest {

    private ClassLoader classLoader = getClass().getClassLoader();
    private File fileToLoad = new File(classLoader.getResource("books-toscrape.html").getFile());
    private String className = "side_categories";
    private DomEngine sut;

    @Before
    public void init() throws IOException {
        sut = new DomEngine(fileToLoad);
    }

    @Test
    public void when_get_prices_size_should_be_20() {
        ArrayList<String> result = sut.getElementTextByClass("price_color");
        assertThat(result.size(), equalTo(20));
    }

    @Test
    public void when_get_class_side_categories_should_be_return_only_one(){
        int expectedSize = 1;

        ArrayList<Element> elemtns = sut.getElementsByClass(className);

        assertThat(expectedSize, equalTo(elemtns.size()));
    }

    @Test
    public void when_get_categories_list_should_be_51(){
        int expectedSize = 51;

        Element categoriesListComponent = sut.getElementsByClass(className).get(0);
        ArrayList<Element> elementsInList = sut.getLiFromListElement(categoriesListComponent);

        assertThat(expectedSize, equalTo(elementsInList.size()));
    }
}