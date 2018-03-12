package com.gepardec.sypoc.routes;

import com.gepardec.sypoc.constants.Endpoints;
import com.gepardec.sypoc.processors.ConaxProcessor;
import com.gepardec.sypoc.service.api.ConaxService;
import com.gepardec.sypoc.utils.LogHelper;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TvOutgoingRoute extends RouteBuilder {

    @Autowired
    private ConaxService conaxService;
    @Autowired
    private ConaxProcessor conaxProcessor;

    public void configure() {
        log.info(LogHelper.ROUTE_BUILDER_STARTING);

        from(Endpoints.DIRECT_TV_OUTGOING).log(LogHelper.buildReceivedMessageOnEndpoint(Endpoints.DIRECT_TV_OUTGOING))
                                          .process(conaxProcessor).bean(conaxService);

        log.info(LogHelper.ROUTE_BUILDER_FINISHED);
    }
}
