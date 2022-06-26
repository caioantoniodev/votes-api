package edu.mentorship.votes;

import edu.mentorship.votes.infra.config.properties.KafkaProperties;
import edu.mentorship.votes.infra.config.properties.MongoProperties;
import edu.mentorship.votes.infra.config.properties.RedisProperties;
import edu.mentorship.votes.infra.config.properties.ThreadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties(value = {
        KafkaProperties.class,
        RedisProperties.class,
        MongoProperties.class,
        ThreadProperties.class
})
public class VotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotesApplication.class, args);
    }

}
