package edu.mentorship.votes;

import edu.mentorship.votes.structure.config.properties.KafkaProperties;
import edu.mentorship.votes.structure.config.properties.MongoProperties;
import edu.mentorship.votes.structure.config.properties.RedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties(value = {
        KafkaProperties.class,
        RedisProperties.class,
        MongoProperties.class
})
public class VotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotesApplication.class, args);
    }

}
