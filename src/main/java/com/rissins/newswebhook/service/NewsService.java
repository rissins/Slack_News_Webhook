package com.rissins.newswebhook.service;

import com.rissins.newswebhook.domain.News;
import com.rissins.newswebhook.dto.NewsResponse;
import com.rissins.newswebhook.repository.NewsRepository;
import com.rissins.newswebhook.util.crawling.Crawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {

    private final List<Crawler> crawlers;
    private final NewsRepository newsRepository;

    @Transactional(readOnly = true)
    public NewsResponse getRecentlyNews() {
        log.info("news 카운트 값 = {}", findNewsCount());
        News news = newsRepository.findById(findNewsCount()).get();
        return news.toResponse();
    }

    public long findNewsCount() {
        return newsRepository.count();
    }

    @Transactional
    public void save() throws IOException {
        for (Crawler crawler : crawlers) {
            News news = crawler.getWords();
            newsRepository.save(news);
        }
    }
}
