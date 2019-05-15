package com.urbanojvr.jsoupproofs.searcher;

import com.urbanojvr.jsoupproofs.docloader.DocumentLoader;
import com.urbanojvr.jsoupproofs.domain.Product;
import com.urbanojvr.jsoupproofs.filemanager.JsonSerializer;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class ProductSearcher implements Searcher{

    private JsonSerializer serializer;
    private ArrayList<String> matchingLinks;
    private ArrayList<Product> matchingProducts;

    public ProductSearcher(){
        serializer = new JsonSerializer();
        matchingLinks = new ArrayList<>();
        matchingProducts = new ArrayList<>();
    }

    @Override
    public ArrayList<String> searchText(String fileName, String target) throws IOException {
        ArrayList<Product> products = serializer.readJsonProducts(fileName);
        for(Product product : products){
            Element body = new DocumentLoader(product.getUrl()).getBody();
            if(!body.getElementsContainingText(target).isEmpty()){
                matchingLinks.add(product.getUrl());
                matchingProducts.add(product);
            }
        }
        return matchingLinks;
    }

    public ArrayList<Product> getMatchingProducts(){
        return matchingProducts;
    }
}
