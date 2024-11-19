package com.xncoder.devtalker.Controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
	
	private static String requestBody;
	
	@PostMapping("/message")
	public Map<String, String> sendMessage(@RequestBody String message) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth("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");

        requestBody = "{"
                + "\"model\": \"llama3-8b-8192\","
                + "\"messages\": [{"
                + "\"role\": \"user\","
                + "\"content\": \"" + message + " Only pull data from stackoverflow\""
                + "}]"
                + "}";
        
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.groq.com/openai/v1/chat/completions",
                HttpMethod.POST,
                entity,
                String.class
        );

        String content = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            JsonNode choicesNode = rootNode.path("choices");
            if (choicesNode.isArray() && choicesNode.size() > 0) {
                JsonNode messageNode = choicesNode.get(0).path("message");
                content = messageNode.path("content").asText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> res = new HashMap<>();
        res.put("response", content);
        return res;
    }
}
