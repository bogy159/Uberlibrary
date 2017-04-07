package at.tuwien.innovation.group7.config;

import com.typesafe.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(Config config) {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout((int) config.getDuration("rest.connectTimeout", TimeUnit.MILLISECONDS));
        clientHttpRequestFactory.setReadTimeout((int)config.getDuration("rest.readTimeout", TimeUnit.MILLISECONDS));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
