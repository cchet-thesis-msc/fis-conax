package com.gepardec.sypoc.service.impl;

import com.gepardec.sypoc.service.api.ResponseService;
import com.gepardec.sypoc.xml.message.response10.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 3/2/18
 */
@Component
public class ResponseServiceImpl implements ResponseService {

    private final Logger log;

    public ResponseServiceImpl() {
        this.log = LoggerFactory.getLogger(ResponseServiceImpl.class);
    }

    @Override
    public void send(Response message) {
        log.info("ResponseService called");
    }
}
