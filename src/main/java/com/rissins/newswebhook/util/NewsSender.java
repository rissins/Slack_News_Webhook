package com.rissins.newswebhook.util;

import com.rissins.newswebhook.dto.NewsResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class NewsSender {

    @Value("${slack.url}")
    private String URL;

    public void send(JSONObject jsonObject) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        // send the post request
        HttpEntity<String> entity = new HttpEntity<>(jsonObject.toJSONString(), headers);
        restTemplate.postForEntity(URL, entity, String.class);
    }

    public JSONObject convertNewsResponseToJson(NewsResponse newsResponse) {
        Map<String, String> titleMap = new HashMap<>();
        titleMap.put("type", "mrkdwn");
        titleMap.put("text", newsResponse.getTitle());

        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("type", "mrkdwn");
        contentMap.put("text", newsResponse.getContent());

        JSONObject jsonObject = new JSONObject();

        JSONObject titleData = new JSONObject();
        titleData.put("type", "section");
        titleData.put("text", titleMap);

        JSONObject contentData = new JSONObject();
        contentData.put("type", "section");
        contentData.put("text", contentMap);

        jsonObject.putAll(titleData);
        jsonObject.putAll(contentData);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(titleData);
        jsonArray.add(contentData);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("blocks", jsonArray);

        return jsonObject1;
    }
}
