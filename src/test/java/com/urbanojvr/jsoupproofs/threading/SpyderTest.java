package com.urbanojvr.jsoupproofs.threading;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SpyderTest {

    private Spyder sut;

    @Before
    public void init(){
        sut = new Spyder("https://elmundo.es");
    }

    @Test
    public void crawl() throws IOException {
        sut.crawl();
    }
}