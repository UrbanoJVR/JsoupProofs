package com.urbanojvr.jsoupproofs.searcher;

import com.urbanojvr.jsoupproofs.docloader.DocumentLoader;
import com.urbanojvr.jsoupproofs.filemanager.JsonSerializer;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class SearchEngine implements Searcher {

    private JsonSerializer serializer;
    private ArrayList<String> matches;

    public SearchEngine(){
        serializer = new JsonSerializer();
        matches = new ArrayList<>();
    }

    @Override
    public ArrayList<String> searchText(String fileName, String target) throws IOException {
        ArrayList<String> links = serializer.readJsonFile(fileName);
        for(String link : links){
            Element body = new DocumentLoader(link).getBody();
            if(!body.getElementsContainingText(target).isEmpty()){
                matches.add(link);
            }
        }
        return matches;
    }
}
