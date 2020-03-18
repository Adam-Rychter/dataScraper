package cz.rychter.covid.datascraper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DatascraperApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(DatascraperApplication.class);

    @Autowired
    private WikiDataScraper wikiDataScraper;

    public static void main(String[] args) {
        LOG.info("Starting application");
        SpringApplication.run(DatascraperApplication.class, args);
        LOG.info("Stopping application");
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
