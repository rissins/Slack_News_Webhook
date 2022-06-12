package com.rissins.newswebhook.util.crawling;

import lombok.Getter;

@Getter
public enum Site {

    NAVER("https://search.naver.com/search.naver?where=news&sm=tab_pge&query=속보&start=", "news_tit", "news_dsc");

    private String url;
    private String titleClassName;
    private String contentClassName;

    Site(String url, String titleClassName, String contentClassName) {
        this.url = url;
        this.titleClassName = titleClassName;
        this.contentClassName = contentClassName;
    }
}
