package at.tuwien.innovation.group7.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(MongoConfig.class);

    @Bean
    @Autowired
    public MongoClient mongoClient(Config config) throws UnknownHostException {
        return new MongoClient(
                config.getString("mongodb.host"),
                config.getInt("mongodb.port")
        );
    }

    @Bean
    @Autowired
    public MongoDatabase mongoDatabase(Config config,  MongoClient mongoClient) {
        MongoDatabase db = mongoClient.getDatabase(config.getString("mongodb.database"));
        for(;;) {
            try {
                 db.getName();
                LOG.info("Successfully connected to {}", db.getName());
                return db;
            } catch (Exception e) {
                LOG.warn("Unable to connect to mongo db '{}' at {}:{} because: {}",
                        config.getString("mongodb.database"),
                        config.getString("mongodb.host"),
                        config.getInt("mongodb.port"),
                        e.getMessage());
            }
            try {
                LOG.info("Auto retry in {}s...", config.getDuration("mongodb.connect-retry-interval",
                        TimeUnit.SECONDS));
                Thread.sleep(config.getDuration("mongodb.connect-retry-interval",
                        TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Interrupted while waiting for mongo db connection");
            }
        }
    }
}
