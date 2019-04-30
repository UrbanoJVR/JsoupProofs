package com.urbanojvr.jsoupproofs.dataextractor;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class SintaxEngineTest {

    private ClassLoader classLoader = getClass().getClassLoader();
    private File fileToLoad = new File(classLoader.getResource("books-toscrape.html").getFile());
    private SintaxEngine sut;

    @Before
    public void init() throws IOException {
        sut = new SintaxEngine(fileToLoad);
    }

    @Test
    public void when_get_ahref_should_return_not_empty_list_with_size_94() {
        Elements elements = sut.getAWithRef();

        assertEquals(94, elements.size());
    }

    @Test
    public void when_get_jpg_images_should_return_list_with_20_elemts() {
        Elements elements = sut.getImages("jpg");

        assertEquals(20, elements.size());
    }

    @Test
    public void when_get_png_images_should_returl_empty_list(){
        Elements elements = sut.getImages("png");

        assertEquals(0, elements.size());
    }

    @Test
    public void when_get_page_inner_div_should_be_two() {
        Elements elements = sut.searchElementAndClass("div", "page_inner");

        assertEquals(2, elements.size());
    }
}