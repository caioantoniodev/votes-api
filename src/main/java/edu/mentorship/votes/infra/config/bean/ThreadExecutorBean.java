package edu.mentorship.votes.infra.config.bean;

import edu.mentorship.votes.infra.config.properties.ThreadProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadExecutorBean {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor(ThreadProperties threadProperties) {
        var threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        threadPoolTaskExecutor.setCorePoolSize(threadProperties.getCore());
        threadPoolTaskExecutor.setMaxPoolSize(threadProperties.getPoolSize());
        threadPoolTaskExecutor.setQueueCapacity(threadProperties.getQueueCapacity());

        return threadPoolTaskExecutor;
    }

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster applicationEventMulticaster() {
        var simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();

        simpleApplicationEventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());

        return simpleApplicationEventMulticaster;
    }
}
