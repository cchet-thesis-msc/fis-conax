package com.gepardec.sypoc.service.impl;

import com.gepardec.sypoc.service.api.ConaxService;
import com.gepardec.sypoc.service.api.ResponseService;
import com.gepardec.sypoc.transformer.ConaxTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConaxServiceImpl implements ConaxService {

    @Autowired
    private ResponseService responseService;

    private final Logger log;

    public ConaxServiceImpl() {
        this.log = LoggerFactory.getLogger(ConaxServiceImpl.class);
    }

    @Override
    public void process(String msg) {
        log.info("ConaxService called");
        responseService.send(ConaxTransformer.conaxToResponse10(msg));
    }
}
