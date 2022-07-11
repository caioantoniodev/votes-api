package edu.mentorship.votes.infra.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisBean {

    @Bean
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {
        var template = new RedisTemplate<String, String>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
