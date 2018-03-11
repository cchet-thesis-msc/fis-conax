/**
 * Copyright 2005-2016 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.gepardec.sypoc;

import com.gepardec.sypoc.constants.Endpoints;
import com.gepardec.sypoc.wsdl.conax.outgoingmessage.OutgoingMessage;
import com.gepardec.sypoc.wsdl.conax.outgoingmessage.OutgoingMessagePortType;
import com.gepardec.sypoc.wsdl.incomingmessage.IncomingMessagePortType;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.CxfSpringEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.cxf.Bus;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.LinkedList;

/**
 * A spring-boot application that includes a Camel route builder to setup the Camel routes
 */
@SpringBootApplication
@ImportResource({"classpath:/spring/camel-context.xml"})
@PropertySource("classpath:/serviceConfiguration.properties")
@PropertySource("${CONFIG_LOCATION_EXTERNAL}")
@ComponentScan(basePackageClasses = Application.class)
@EnableAutoConfiguration
public class Application {

    // must have a main method spring-boot can run
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private final Bus bus;

    @Autowired
    public Application(final Bus bus) {
        this.bus = bus;
    }

    @Bean
    RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
        return restTemplate;
    }

    @Bean
    DozerBeanMapper createDozerBeanMapper() {
        final DozerBeanMapper mapper = new DozerBeanMapper(new LinkedList<String>() {{
            add("/dozer/map.xml");
        }});

        return mapper;
    }

    @Bean
    OutgoingMessagePortType createConaxWebserviceClient(@Value("${wsdl.conax}") final String wsdlLocation) throws Exception {
        final OutgoingMessage conaxService = new OutgoingMessage(new URL(wsdlLocation));
        return conaxService.getOutgoingMessagePort();
    }

    @Bean(Endpoints.INCOMING_MESSAGE)
    CxfEndpoint createIncomingMessageCxfEndpoint() {
        CxfEndpoint cxfEndpoint = new CxfSpringEndpoint();
        cxfEndpoint.setAddress(String.format("/%s", Endpoints.INCOMING_MESSAGE));
        cxfEndpoint.setWsdlURL("wsdl/IncomingMessage.wsdl");
        cxfEndpoint.setServiceClass(IncomingMessagePortType.class);
        cxfEndpoint.setDataFormat(DataFormat.POJO);
        cxfEndpoint.setBus(bus);

        return cxfEndpoint;
    }
}
