package at.tuwien.innovation.group7.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {

    @Bean
    public Config config() {
        return ConfigFactory.load();
    }

}
