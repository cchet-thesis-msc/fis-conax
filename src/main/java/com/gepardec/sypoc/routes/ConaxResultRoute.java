package com.gepardec.sypoc.routes;

import com.gepardec.sypoc.constants.Endpoints;
import com.gepardec.sypoc.constants.Processors;
import com.gepardec.sypoc.service.api.ResponseService;
import com.gepardec.sypoc.utils.LogHelper;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ConaxResultRoute extends RouteBuilder {

    private static final String REF_OUTGOING_RESULT = "ResponseService";
    private final Processor conaxResultProcessor;
    private final ResponseService responseService;

    @Autowired
    public ConaxResultRoute(@Qualifier(Processors.CONAX_RESULT) final Processor conaxResultProcessor,
                            final ResponseService responseService) {
        this.conaxResultProcessor = conaxResultProcessor;
        this.responseService = responseService;
    }

    public void configure() {
        log.info(LogHelper.ROUTE_BUILDER_STARTING);

        from(Endpoints.DIRECT_CONEX_RESULT).log(LogHelper.buildReceivedMessageOnEndpoint(Endpoints.DIRECT_CONEX_RESULT))
                                           .process(conaxResultProcessor).bean(responseService);

        /*from(ServiceDefinitions.EP_SWITCHYARD + ServiceDefinitions.SVC_CONAX_RESULT).log(
                "Received message for 'ConaxResult' : ${body}")
                                                                                    .process(resultProcessor)
                                                                                    .to(ServiceDefinitions.EP_SWITCHYARD + REF_OUTGOING_RESULT);*/

        log.info(LogHelper.ROUTE_BUILDER_FINISHED);
    }

}
