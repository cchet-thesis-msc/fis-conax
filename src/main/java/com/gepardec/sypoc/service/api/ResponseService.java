package com.gepardec.sypoc.service.api;

import com.gepardec.sypoc.xml.message.response10.Response;

// Switchyard interface which gets implemented by switchyard, which sends the response
// Could be implemented by spring-ws module
public interface ResponseService {

    void send(Response message);
}
