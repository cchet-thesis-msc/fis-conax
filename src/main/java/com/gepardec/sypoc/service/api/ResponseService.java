package com.gepardec.sypoc.service.api;

import com.gepardec.sypoc.wsdl.conax.xml.messagerequest.Message;

public interface ResponseService {

    void send(Message message);
}
