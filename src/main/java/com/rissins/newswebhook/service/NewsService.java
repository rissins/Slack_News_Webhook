package com.rissins.newswebhook.service;

import com.rissins.newswebhook.domain.News;
import com.rissins.newswebhook.dto.NewsResponse;
import com.rissins.newswebhook.repository.NewsRepository;
import com.rissins.newswebhook.util.crawling.Crawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final List<Crawler> crawlers;
    private final NewsRepository newsRepository;

    @Transactional(readOnly = true)
    public Optional<News> getRecentlyNews() {
        return newsRepository.findById(findNewsCount() + 1);
    }

    public long findNewsCount() {
        return newsRepository.count();
    }

    @Transactional
    public void save() throws IOException {
//    List<String> words = new ArrayList<>();
        for (Crawler crawler : crawlers) {
            News news = crawler.getWords();
            newsRepository.save(news);
        }
    }
}
