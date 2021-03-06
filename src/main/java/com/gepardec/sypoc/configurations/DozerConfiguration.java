package com.gepardec.sypoc.configurations;

import org.apache.camel.CamelContext;
import org.apache.camel.converter.dozer.DozerBeanMapperConfiguration;
import org.apache.camel.converter.dozer.DozerTypeConverterLoader;
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
            // we need to add classpath protocol too
            add("classpath:/dozer/mappings.xml");
        }});
        return dozerConfig;
    }

    @Bean
    DozerTypeConverterLoader createDozerTypeConverterLoader(final CamelContext camelContext, final DozerBeanMapperConfiguration dozerConfig){
        return new DozerTypeConverterLoader(camelContext, dozerConfig);
    }
}
