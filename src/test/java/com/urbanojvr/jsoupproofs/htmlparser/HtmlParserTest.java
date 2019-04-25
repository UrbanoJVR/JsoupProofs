package com.urbanojvr.jsoupproofs.htmlparser;

import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class HtmlParserTest {

    private HtmlParser sut;
    private String text = "<html><head><title>Parsing string to HTML</title></head>"
            + "<body><p>Parsing String into a HTML document</p>" +
            "<p>JSOUP PROOF OF CONCEPT</p></body></html>";
    private String title = "Parsing string to HTML";

    @Test
    public void should_parse_text_string_correctly() {
        String body = "Parsing String into a HTML document JSOUP PROOF OF CONCEPT";

        sut = new HtmlParser(text);
        Document doc = sut.getDoc();

        assertThat(doc.title(), equalTo(title));
        assertThat(doc.body().text(), equalTo(body));
        System.out.println(doc.body().text());
    }

    @Test
    public void should_save_correctly_the_base_URI() {
        String baseUrl = "http://myweb.com/category/article/subarticle-1";

        sut = new HtmlParser(text, baseUrl);
        Document doc = sut.getDoc();

        assertThat(doc.baseUri(), equalTo(baseUrl));
        assertThat(doc.title(), equalTo(title));
    }

    @Test
    public void should_parse_html_body_and_head_should_be_empty(){
        String body = "<p>Paragraph 1</p>" +
                "<p>Paragraph 2</p>";
        String expectedBody = "<body>\n" +
                " <p>Paragraph 1</p>\n" +
                " <p>Paragraph 2</p>\n" +
                "</body>";

        sut = new HtmlParser();
        sut.parseBody(body);
        Document doc = sut.getDoc();

        assertThat(doc.body().toString(), equalTo(expectedBody));
        assertTrue(doc.head().text().isEmpty());
    }
}