package com.urbanojvr.jsoupproofs.dataextractor;

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
}