package cz.rychter.covid.datascraper;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CovidData {

    private Map<String, List<Pair<String,String>>> data;

}
