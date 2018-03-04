package com.gepardec.sypoc.routes;

import com.gepardec.sypoc.constants.Endpoints;
import com.gepardec.sypoc.constants.Processors;
import com.gepardec.sypoc.service.api.ConaxService;
import com.gepardec.sypoc.utils.LogHelper;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TvOutgoingRoute extends RouteBuilder {

    private final ConaxService conaxService;
    private final Processor conaxProcessor;

    @Autowired
    public TvOutgoingRoute(@Qualifier(Processors.CONAX) Processor conaxProcessor,
                           final ConaxService conaxService) {
        this.conaxProcessor = conaxProcessor;
        this.conaxService = conaxService;
    }

    public void configure() {
        log.info(LogHelper.ROUTE_BUILDER_STARTING);

        from(Endpoints.DIRECT_TV_OUTGOING).log(LogHelper.buildReceivedMessageOnEndpoint(Endpoints.DIRECT_TV_OUTGOING))
                                          .process(conaxProcessor).bean(conaxService);

        log.info(LogHelper.ROUTE_BUILDER_FINISHED);
    }
}
