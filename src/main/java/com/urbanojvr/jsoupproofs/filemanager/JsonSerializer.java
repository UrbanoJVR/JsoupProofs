package com.urbanojvr.jsoupproofs.filemanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.urbanojvr.jsoupproofs.domain.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonSerializer {

    private Gson gson;

    public JsonSerializer(){
        gson = new Gson();
    }

    public void writeFile(Object objectToSerialize, String filename) throws IOException {
        try(FileWriter fileWriter = new FileWriter(filename)){
            gson.toJson(objectToSerialize, fileWriter);
            System.out.println("SERIALIZED TO :::::::::: " + filename);
            fileWriter.flush();
        } catch (Exception error){
            System.err.println("ERROR WRITING JSON FILE : " + error.getMessage());
        }
    }

    public ArrayList<String> readJsonFile(String filename) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(filename));
        TypeToken<ArrayList<String>> token = new TypeToken<ArrayList<String>>() {};
        ArrayList<String> linksList = gson.fromJson(reader, token.getType());
        return linksList;
    }

    public ArrayList<Product> readJsonProducts(String filename) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(filename));
        TypeToken<ArrayList<Product>> token = new TypeToken<ArrayList<Product>>() {};
        ArrayList<Product> products = gson.fromJson(reader, token.getType());
        return products;
    }
}
