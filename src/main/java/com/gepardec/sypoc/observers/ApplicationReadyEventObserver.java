package com.gepardec.sypoc.observers;

import com.gepardec.sypoc.swagger.client.conax.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 03/11/18
 */
@Component
public class ApplicationReadyEventObserver implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ApiClient conaxApiClient;

    @Value("${conax.basePath}")
    private String conaxBasePath;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        // Initialize ApiClient with provided basePath
        conaxApiClient.setBasePath(conaxBasePath);
    }
}