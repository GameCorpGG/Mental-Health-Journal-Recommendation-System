package com.Rachit.mental_health.service;

import com.Rachit.mental_health.entity.Content;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class YouTubeApiService {
    private final String API_KEY = "AIzaSyCYeq4t_b_YzzNm1rcekYKnaG2uvaZPVLs";
    private final String API_URL = "https://www.googleapis.com/youtube/v3/videos";

    public Content fetchVideoAsContent(String videoId, String category) {
        String url = API_URL + "?part=snippet&id=" + videoId + "&key=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        JSONObject json = new JSONObject(response);
        if (json.getJSONArray("items").isEmpty()) {
            // No video found for this ID, handle gracefully
            return null;
        }
        JSONObject snippet = json.getJSONArray("items").getJSONObject(0).getJSONObject("snippet");

        Content content = new Content();
        content.setTitle(snippet.getString("title"));
        content.setDescription(snippet.getString("description"));
        content.setUrl("https://www.youtube.com/watch?v=" + videoId);
        content.setCategory(category);
        // Set other fields as needed

        return content;
    }
}