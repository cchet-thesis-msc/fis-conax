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

    private final ResponseService responseService;
    private final Logger log;

    @Autowired
    public ConaxServiceImpl(ResponseService responseService) {
        this.responseService = responseService;

        this.log = LoggerFactory.getLogger(ConaxServiceImpl.class);
    }

    @Override
    public void process(String msg) {
        log.info("ConaxService called");
        responseService.send(ConaxTransformer.conaxToResponse10(msg));
    }

}
