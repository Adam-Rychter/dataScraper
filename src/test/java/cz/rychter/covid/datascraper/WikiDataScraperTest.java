package cz.rychter.covid.datascraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WikiDataScraperTest {

    private HtmlPage htmlPage;

    private static Logger LOG = LoggerFactory
            .getLogger(WikiDataScraperTest.class);

    @Autowired
    private WikiDataScraper wikiDataScraper;

    @BeforeEach
    void setUp() {

        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setCssEnabled(false);
        try {
            File resource = new ClassPathResource("TestPage.html").getFile();
            URL fileUrl = resource.toURI().toURL();
//            LOG.debug("Debbuging url: ",fileUrl);
            htmlPage = webClient.getPage(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void scrape() {
        assertNotNull(htmlPage);
        System.out.println(wikiDataScraper.scrape());
    }
}