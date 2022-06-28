package edu.mentorship.votes.infra.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Positive;

@ConfigurationProperties(prefix = "votes-api.configurations.thread")
@Validated
@Data
public class ThreadProperties {

    @Positive
    private Integer core;

    @Positive
    private Integer poolSize;

    @Positive
    private Integer queueCapacity;
}
