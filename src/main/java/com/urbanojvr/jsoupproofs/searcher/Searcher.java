package com.urbanojvr.jsoupproofs.searcher;

import java.io.IOException;
import java.util.ArrayList;

public interface Searcher {
    ArrayList<String> searchText(String fileName, String target) throws IOException;
}
