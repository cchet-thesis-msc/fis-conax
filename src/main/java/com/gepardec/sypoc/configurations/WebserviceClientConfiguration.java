package com.gepardec.sypoc.configurations;

import com.gepardec.sypoc.wsdl.conax.outgoingmessage.OutgoingMessage;
import com.gepardec.sypoc.wsdl.conax.outgoingmessage.OutgoingMessagePortType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 03/12/18
 */
@Configuration
public class WebserviceClientConfiguration {

    @Bean
    OutgoingMessagePortType createConaxWebserviceClient(@Value("${conax.wsdl.remote.url}") final String wsdlLocation) throws Exception {
        final OutgoingMessage conaxService = new OutgoingMessage(new URL(wsdlLocation));
        return conaxService.getOutgoingMessagePort();
    }
}
