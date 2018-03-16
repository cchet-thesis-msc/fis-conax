package com.gepardec.sypoc.routes;

import com.gepardec.sypoc.constants.Endpoints;
import com.gepardec.sypoc.constants.ServiceDefinitions;
import com.gepardec.sypoc.processors.ConaxProcessor;
import com.gepardec.sypoc.service.api.ConaxJsonService;
import com.gepardec.sypoc.service.api.ConaxService;
import com.gepardec.sypoc.utils.LogHelper;
import com.gepardec.sypoc.wsdl.incomingmessage.MessageRequest;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class IncomingRoute extends RouteBuilder {

    @Autowired
    private ConaxProcessor conaxProcessor;
    @Autowired
    private ConaxService conaxService;
    @Autowired
    private ConaxJsonService conaxJsonService;
    @Autowired
    @Qualifier(Endpoints.INCOMING_MESSAGE)
    private Endpoint incomingMessageCxfEndpoint;

    public IncomingRoute() {
    }

    /**
     * The Camel route is configured via this method.  The from endpoint is required to be a SwitchYard service.
     */
    public void configure() {
        log.info(LogHelper.ROUTE_BUILDER_STARTING);
        final String endpointUri = incomingMessageCxfEndpoint.getEndpointUri();

        from(incomingMessageCxfEndpoint)
                .log(LogHelper.buildReceivedMessageOnEndpoint(endpointUri))
                .choice()
                // internet
                .when().simple(getSimpleExpressionFor(ServiceDefinitions.MessageType.TYPE_INTERNET))
                .bean(conaxJsonService)
                .process(conaxProcessor)
                .bean(conaxService).to(Endpoints.DIRECT_CONEX_RESULT) // Order important because direct:* route transforms message !!!!
                // mail
                .when().simple(getSimpleExpressionFor(ServiceDefinitions.MessageType.TYPE_MAIL))
                .process(conaxProcessor).bean(conaxService) // CallIgnito missing !!!!!
                // digitv
                .when().simple(getSimpleExpressionFor(ServiceDefinitions.MessageType.TYPE_DIGITV))
                .to(Endpoints.DIRECT_TV_OUTGOING)
                .when((o) -> true).log(LogHelper.buildDefaultCaeReached(endpointUri))
                .endChoice();

        log.info(LogHelper.ROUTE_BUILDER_FINISHED);
    }

    private String getSimpleExpressionFor(String messageType) {
        return String.format("${bodyAs(%s).getMessage().getService().getName()} == '%s'", MessageRequest.class.getName(), messageType);
    }
}
