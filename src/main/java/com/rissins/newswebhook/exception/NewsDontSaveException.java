package com.rissins.newswebhook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason = "OverLap Crawling News ")
public class NewsDontSaveException extends RuntimeException {
    private static final String MESSAGE = "중복된 뉴스입니다.";
    public NewsDontSaveException(String detail) {
        super(MESSAGE + "News Url = " + detail);
    }
}
