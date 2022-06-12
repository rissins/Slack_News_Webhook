package com.rissins.newswebhook.controller;

import com.rissins.newswebhook.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NewsRestController {

    private final NewsService newsService;

    @PostMapping("/news")
    public void save() throws IOException {
        newsService.save();
    }
}
