package com.urbanojvr.jsoupproofs.crawler;

import com.urbanojvr.jsoupproofs.docloader.DocumentLoader;
import com.urbanojvr.jsoupproofs.domain.Product;
import com.urbanojvr.jsoupproofs.filemanager.JsonSerializer;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class EcommercePatternEngine implements Crawler {

    private static final int FIRST_PAGE_NUMBER = 1;
    private static final String DEAFULT_JSON_FILE_NAME = "src/main/resources/links_ecom.json";

    private String actualUrl;
    ArrayList<Product> products;
    private String productContainerTag;
    private String priceTag;
    private String titleTag;
    private String urlTag;
    private int pageLimit;
    private String nextPageTag;
    private String jsonFileName;

    public EcommercePatternEngine(String parentUrl,
                                  String productContainerTag,
                                  String priceTag,
                                  String titleTag,
                                  String urlTag,
                                  int pageLimit,
                                  String nextPageTag) {
        this.productContainerTag = productContainerTag;
        this.priceTag = priceTag;
        this.titleTag = titleTag;
        this.urlTag = urlTag;
        this.products = new ArrayList<>();
        this.pageLimit = pageLimit;
        this.actualUrl = parentUrl;
        this.nextPageTag = nextPageTag;
        jsonFileName = DEAFULT_JSON_FILE_NAME;
    }

    @Override
    public void crawl() throws IOException {
        parsePage(actualUrl, FIRST_PAGE_NUMBER);
        saveProducts();
    }

    public void parsePage(String pageUrl, int pageNum) throws IOException {
        System.out.println("PAGE :: " + pageNum);
        Document document = new DocumentLoader(pageUrl).getDoc();
        Elements productContainers = document.select(productContainerTag); //article.product_prod
        for (Element container : productContainers) {
            Product p = createProduct(container);
            products.add(p);
        }
        if (pageNum < pageLimit && !document.select(nextPageTag).isEmpty()){
            pageNum ++;
            parsePage(getUrlFromContainer(document.selectFirst(nextPageTag)), pageNum);
        } else {
            System.out.println(" ------ FINISHED ------");
        }
    }

    @Override
    public ArrayList<String> getScrapedLinks() {
        ArrayList<String> scrapedLinks = new ArrayList<>();
        for(Product p : products){
            scrapedLinks.add(p.getUrl());
        }
        return scrapedLinks;
    }

    public Product createProduct(Element container) {
        String title = getTitleFromContainer(container);
        String price = getPriceFromContainer(container);
        String url = getUrlFromContainer(container);
        System.out.println(url);
        System.out.println(title);
        System.out.println(price);
        return new Product(url, title, price);
    }

    public String getTitleFromContainer(Element container) {
        Element element = container.selectFirst(titleTag);
        String title = element.attr("title");
        return title;
    }

    public String getPriceFromContainer(Element container) {
        return container.selectFirst(priceTag).text();
    }

    public String getUrlFromContainer(Element container) {
        return container.selectFirst(urlTag).attr("abs:href");
    }

    private void saveProducts() throws IOException {
        JsonSerializer serializer = new JsonSerializer();
        serializer.writeFile(products, jsonFileName);
    }
}
