package com.rissins.newswebhook.controller;

import com.rissins.newswebhook.dto.NewsResponse;
import com.rissins.newswebhook.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
public class ApiRestController {

    private final NewsService newsService;

    @GetMapping("/news")
    public NewsResponse getRecentNews() {
        return newsService.getRecentlyNews();
    }
}
