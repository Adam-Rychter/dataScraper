package cz.rychter.covid.datascraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Getter
@Setter
@Component
public class WikiDataScraper {

    private static Logger LOG = LoggerFactory
            .getLogger(WikiDataScraper.class);

    private String strUrl = "https://en.wikipedia.org/wiki/Template:2019%E2%80%9320_coronavirus_pandemic_data/WHO_situation_reports";

//    Coronavirus (SARS-CoV-2) cases in January 2020[notes 1][1]
//    Coronavirus (SARS-CoV-2) cases in first half of February 2020
//    Coronavirus (SARS-CoV-2) cases in second half of February 2020
//    Coronavirus (SARS-CoV-2) cases in first half of March 2020
    public String scrape() {
       HtmlPage page = getHtmlPage();
       List<HtmlElement> tables = (List<HtmlElement>) page.getByXPath("//*[@id=\"mw-content-text\"]/div/table");

        tables.remove(0);
       StringBuilder sb = new StringBuilder("");
        for (HtmlElement el : tables) {
            String tableToParse = ((HtmlElement)el.getFirstByXPath("./caption")).getTextContent();

            List<HtmlElement> data = (List<HtmlElement>) el.getByXPath("./tbody/tr/th");

            for (HtmlElement el1 : data) {
                System.out.println(el1.getTextContent());
            }

            sb.append(tableToParse);
            sb.append(el.toString());
            sb.append("\n");
        }
       return sb.toString();
    }

    private HtmlPage getHtmlPage(){
        HtmlPage page = null;
        WebClient webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);

        try {
            page = webClient.getPage(strUrl);
        } catch (MalformedURLException e) {
            LOG.error("URL is NOT understood");
            e.printStackTrace();
        } catch (IOException e) {
            LOG.error("Could not download page");
            e.printStackTrace();
        }finally {
            return page;
        }
    }

}
