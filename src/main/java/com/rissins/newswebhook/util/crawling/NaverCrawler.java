package com.rissins.newswebhook.util.crawling;

import com.rissins.newswebhook.domain.News;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class NaverCrawler implements Crawler {


    @Override
    public News getWords() throws IOException {
        List<News> crawlingTexts = new ArrayList<>();

        Connection conn = Jsoup.connect(Site.NAVER.getUrl());
        Document html = conn.get();
        Element newsTitleElement = html.getElementsByClass(Site.NAVER.getTitleClassName()).get(0);
        Element newsContentElement = html.getElementsByClass(Site.NAVER.getContentClassName()).get(0);

        News news = News.builder()
                .title(newsTitleElement.text())
                .content(newsContentElement.text()).build();
//        crawlingTexts.add(newsTitleElement.text());
//        crawlingTexts.add(newsContentElement.text());

        return news;
    }
}
