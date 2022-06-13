package com.rissins.newswebhook.config;

import com.rissins.newswebhook.dto.NewsResponse;
import com.rissins.newswebhook.service.NewsService;
import com.rissins.newswebhook.util.NewsSender;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
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
    private final NewsSender newsSender;

    @Bean
    public Job newsJob() {
        return jobBuilderFactory.get("newsJob")
                .start(crawlingNews())
                .next(sendWebhook())
                .build();
    }

    @Bean
    public Step crawlingNews() {
        return stepBuilderFactory.get("crawlingNews")
                .tasklet((contribution, chunkContext) -> {
                    newsService.save();
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step sendWebhook() {
        return stepBuilderFactory.get("sendWebhook")
                .tasklet((contribution, chunkContext) -> {
                    NewsResponse recentlyNews = newsService.getRecentlyNews();
                    JSONObject jsonObject = newsSender.convertNewsResponseToJson(recentlyNews);
                    newsSender.send(jsonObject);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
