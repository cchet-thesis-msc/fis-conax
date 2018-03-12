package com.gepardec.sypoc.configurations;

import org.apache.camel.converter.dozer.DozerBeanMapperConfiguration;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 03/12/18
 */
@Configuration
public class DozerConfiguration {

    @Bean
    DozerBeanMapperConfiguration createDozerBeanMapper() {
        final DozerBeanMapperConfiguration dozerConfig = new DozerBeanMapperConfiguration();
        dozerConfig.setMappingFiles(new LinkedList<String>() {{
            add("/dozer/map.xml");
        }});

        return dozerConfig;
    }
}
