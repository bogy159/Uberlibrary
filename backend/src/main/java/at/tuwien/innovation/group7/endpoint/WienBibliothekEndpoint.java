package at.tuwien.innovation.group7.endpoint;

import com.typesafe.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class WienBibliothekEndpoint {

    private final Config config;
    private final RestTemplate restTemplate;

    @Autowired
    public WienBibliothekEndpoint(Config config, RestTemplate restTemplate) {
        this.config = config;
        this.restTemplate = restTemplate;
    }

    public String getIndentifiers() {
        return restTemplate.getForObject(
                config.getString("wienBibliothek.listIdentifiers"),
                String.class,
                Collections.emptyMap()
        );
    }


    public String getIndentifiers(String resumptionToken) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("resumptionToken", resumptionToken);

        return restTemplate.getForObject(
                config.getString("wienBibliothek.listIdentifiersWithResumptionToken"),
                String.class,
                params
        );
    }

}
