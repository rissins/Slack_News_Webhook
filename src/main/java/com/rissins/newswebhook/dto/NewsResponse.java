package com.rissins.newswebhook.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsResponse {

    private String title;
    private String content;
    private String url;
}
