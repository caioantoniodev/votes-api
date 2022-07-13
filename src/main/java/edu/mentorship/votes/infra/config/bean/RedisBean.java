package edu.mentorship.votes.infra.config.bean;

import edu.mentorship.votes.infra.config.properties.RedisProperties;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import lombok.RequiredArgsConstructor;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisBean {

    private final RedisProperties redisProperties;

    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        return new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
    }

    @Bean(destroyMethod = "shutdown")
    ClientResources clientResources() {
        return DefaultClientResources.create();
    }

    @Bean
    ClientOptions clientOptions() {
        return ClientOptions.builder()
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .autoReconnect(true)
                .build();
    }

    @Bean
    public LettucePoolingClientConfiguration lettucePoolingClientConfiguration(ClientOptions clientOptions,
                                                                        ClientResources clientResources,
                                                                        org.springframework.boot.autoconfigure.data.redis.RedisProperties redis) {

        var objectGenericObjectPoolConfig = new GenericObjectPoolConfig<>();

        objectGenericObjectPoolConfig.setMaxIdle(redis.getLettuce().getPool().getMaxIdle());
        objectGenericObjectPoolConfig.setMinIdle(redis.getLettuce().getPool().getMinIdle());
        objectGenericObjectPoolConfig.setMaxWaitMillis(redis.getLettuce().getPool().getMaxWait().toMillis());
        objectGenericObjectPoolConfig.setMaxTotal(redis.getLettuce().getPool().getMaxActive());

        return LettucePoolingClientConfiguration.builder()
                .poolConfig(objectGenericObjectPoolConfig)
                .clientOptions(clientOptions)
                .clientResources(clientResources)
                .build();
    }

    @Bean
    RedisConnectionFactory redisConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration,
                                                  LettucePoolingClientConfiguration lettucePoolingClientConfiguration) {

        return new LettuceConnectionFactory(redisStandaloneConfiguration, lettucePoolingClientConfiguration);
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) {

        var template = new RedisTemplate<String, String>();

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        template.setConnectionFactory(redisConnectionFactory);
        template.setEnableTransactionSupport(true);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(stringRedisSerializer);
        template.afterPropertiesSet();

        return template;
    }
}
