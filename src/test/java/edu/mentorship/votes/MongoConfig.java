package edu.mentorship.votes;

import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import edu.mentorship.votes.structure.config.properties.MongoProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.net.UnknownHostException;

@TestConfiguration
public class MongoConfig {

    @Bean
    public MongodConfig mongodConfig(MongoProperties mongoProperties) throws UnknownHostException {

        return MongodConfig.builder()
                .version(Version.V4_4_5)
                .net(new Net(mongoProperties.getPort(), Network.localhostIsIPv6()))
                .build();
    }
}
