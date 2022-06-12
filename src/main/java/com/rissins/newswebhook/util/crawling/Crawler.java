package com.rissins.newswebhook.util.crawling;

import com.rissins.newswebhook.domain.News;

import java.io.IOException;

public interface Crawler {
    News getWords() throws IOException;
}
