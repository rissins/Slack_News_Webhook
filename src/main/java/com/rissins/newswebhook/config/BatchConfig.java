package com.rissins.newswebhook.config;

import com.rissins.newswebhook.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final NewsService newsService;

    @Bean
    public Job newsJob() {

    }

    @Bean
    public Step getNews() {
        return stepBuilderFactory.get("newsStep")
                .tasklet((contribution, chunkContext) -> {
                    newsService.save();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step sendWebhook() {
        return stepBuilderFactory.
    }
}
