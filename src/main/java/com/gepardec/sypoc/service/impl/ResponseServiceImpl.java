package com.gepardec.sypoc.service.impl;

import com.gepardec.sypoc.service.api.ResponseService;
import com.gepardec.sypoc.utils.LogHelper;
import com.gepardec.sypoc.wsdl.conax.outgoingmessage.MessageRequest;
import com.gepardec.sypoc.wsdl.conax.outgoingmessage.OutgoingMessagePortType;
import com.gepardec.sypoc.wsdl.conax.xml.messagerequest.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 3/2/18
 */
@Component
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private OutgoingMessagePortType conaxServiceClient;

    private final Logger log;

    @Autowired
    public ResponseServiceImpl() {
        this.log = LoggerFactory.getLogger(ResponseServiceImpl.class);
    }

    @Override
    public void send(Message message) {
        log.info(LogHelper.CALLING_WEBSERVICE_STARTING);

        final MessageRequest request = new MessageRequest();
        request.setMessage(message);

        conaxServiceClient.messageRequest(request);

        log.info(LogHelper.CALLING_WEBSERVICE_FINISHED);
    }
}
