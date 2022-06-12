package com.rissins.newswebhook.util.crawling;

import java.io.IOException;
import java.util.List;

public interface Crawler {
    List<String> getWords() throws IOException;
}
