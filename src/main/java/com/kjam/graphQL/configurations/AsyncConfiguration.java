package com.kjam.graphQL.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration {
    
    @Bean("ResolverThreadPool")
    public TaskExecutor resolverThreadPoolTaskExecutor() {
        final var executor = new ThreadPoolTaskExecutor();

        executor.setThreadNamePrefix("NINTENDO ::");
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(20);
        executor.initialize();

        return executor;
        // This propagates the SecurityContext to our Async Threads
        // return new DelegatingSecurityContextAsyncTaskExecutor(executor) {
        //     // This helps to prevent memory leads when propogating the Security Context to our async thread
        //     public void shutdown() {executor.destroy();}
        // };
    }
}
