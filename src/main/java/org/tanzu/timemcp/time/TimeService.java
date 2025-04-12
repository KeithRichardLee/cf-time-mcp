package org.tanzu.timemcp.time;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class TimeService {
    private final RestTemplate restTemplate;
    private static final String API_URL = "https://timeapi.io/api/Time/current/zone?timeZone=UTC";

    public TimeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Tool(description = "Get the current time")
    public TimeResponse getCurrentTime() {
        try {
            return restTemplate.getForObject(API_URL, TimeResponse.class);
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to fetch current time", e);
        }
    }

    // Time response model
    public record TimeResponse(
            String year,
            String month,
            String day,
            String hour,
            String minute,
            String seconds,
            String milliSeconds,
            String dateTime,
            String date,
            String time,
            String timeZone,
            String dayOfWeek,
            boolean dstActive
    ) {}
}
