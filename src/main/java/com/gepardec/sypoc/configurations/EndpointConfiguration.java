package com.gepardec.sypoc.configurations;

import com.gepardec.sypoc.constants.Endpoints;
import com.gepardec.sypoc.wsdl.incomingmessage.IncomingMessagePortType;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.CxfSpringEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.cxf.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 03/12/18
 */
@Configuration
public class EndpointConfiguration {

    @Autowired
    private Bus bus;

    @Bean(Endpoints.INCOMING_MESSAGE)
    CxfEndpoint createIncomingMessageCxfEndpoint() {
        CxfEndpoint cxfEndpoint = new CxfSpringEndpoint();
        cxfEndpoint.setAddress(String.format("/%s", Endpoints.INCOMING_MESSAGE));
        cxfEndpoint.setWsdlURL("classpath:/wsdl/IncomingMessage.wsdl");
        cxfEndpoint.setServiceClass(IncomingMessagePortType.class);
        cxfEndpoint.setDataFormat(DataFormat.POJO);
        cxfEndpoint.setBus(bus);

        return cxfEndpoint;
    }
}
