package edu.mentorship.votes.infra.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(prefix="votes-api.configurations.thread")
@Validated
@Data
public class ThreadProperties {

    @NotBlank
    private Integer core;

    @NotBlank
    private Integer poolSize;

    @NotBlank
    private Integer queueCapacity;
}
