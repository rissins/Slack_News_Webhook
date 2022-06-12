package com.rissins.newswebhook.controller;

import com.rissins.newswebhook.domain.News;
import com.rissins.newswebhook.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ApiRestController {

    private final NewsService newsService;

    @GetMapping("/news")
    public Optional<News> getRecentNews() {
        return newsService.getRecentlyNews();
    }
}
