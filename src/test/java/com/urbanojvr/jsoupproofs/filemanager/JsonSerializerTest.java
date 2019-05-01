package com.urbanojvr.jsoupproofs.filemanager;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class JsonSerializerTest {

    private JsonSerializer sut;

    @Before
    public void init(){
        sut = new JsonSerializer();
    }

    @Test
    public void writeFile() {
    }

    @Test
    public void readJsonFile() throws FileNotFoundException {
        ArrayList<String> returned = sut.readJsonFile("links.url");
        for(String link : returned){
            System.out.println(link);
        }
        System.out.println("List size :: " + returned.size());
    }
}