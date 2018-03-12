package com.gepardec.sypoc.processors;

import com.gepardec.sypoc.transformer.ConaxTransformer;
import com.gepardec.sypoc.utils.LogHelper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 03/12/18
 */
@Component
public class ConaxResultProcessor implements Processor {

    private final Logger log;

    public ConaxResultProcessor() {
        this.log = LoggerFactory.getLogger(ConaxResultProcessor.class);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info(LogHelper.PROCESSOR_STARTING);

        Object body = exchange.getIn().getBody();
        exchange.getIn().setBody(ConaxTransformer.conaxToResponse10(body.toString()));
        exchange.getIn().setHeader(Exchange.FILE_NAME, exchange.getIn().getHeader(Exchange.FILE_NAME).toString().replace("emm.esbDone", "xml"));

        log.info(LogHelper.PROCESSOR_FINISHED);
    }

}
