package com.gepardec.sypoc.processors;

import com.gepardec.sypoc.constants.Processors;
import com.gepardec.sypoc.transformer.ConaxTransformer;
import com.gepardec.sypoc.utils.LogHelper;
import com.gepardec.sypoc.wsdl.incomingmessage.MessageRequest;
import com.gepardec.sypoc.xml.fis.conax.xml.messagerequest.Message;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(Processors.CONAX)
public class ConaxProcessor implements Processor {

    private final Logger log;

    public ConaxProcessor() {
        this.log = LoggerFactory.getLogger(ConaxProcessor.class);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info(LogHelper.PROCESSOR_STARTING);

        final Message message = exchange.getIn().getBody(MessageRequest.class).getMessage();
        final String fn = "activate".equals(message.getService().getAction().getName()) ? "ps" : "q" + message.getService().getAction().getData().getOrderNo();

        exchange.getIn().setBody(ConaxTransformer.request10ToConax(message));
        exchange.getIn().setHeader(Exchange.FILE_NAME, fn + ".emm");

        log.info(LogHelper.PROCESSOR_FINISHED);
    }
}