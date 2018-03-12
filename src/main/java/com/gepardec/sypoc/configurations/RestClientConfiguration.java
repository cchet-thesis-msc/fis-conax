package com.gepardec.sypoc.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 03/12/18
 */
@Configuration
public class RestClientConfiguration {

    @Bean
    RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
        return restTemplate;
    }
}
