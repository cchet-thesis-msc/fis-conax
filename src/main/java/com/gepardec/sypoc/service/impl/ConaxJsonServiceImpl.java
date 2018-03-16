package com.gepardec.sypoc.service.impl;

import com.gepardec.sypoc.service.api.ConaxJsonService;
import com.gepardec.sypoc.swagger.client.conax.api.ConaxRestControllerApi;
import com.gepardec.sypoc.swagger.client.conax.model.ImmutableConaxRequest;
import com.gepardec.sypoc.utils.LogHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Thomas Herzog <herzog.thomas81@gmail.com>
 * @since 03/16/18
 */
@Component
public class ConaxJsonServiceImpl implements ConaxJsonService {

    @Autowired
    private ConaxRestControllerApi conaxControllerApi;

    private final Logger log;

    public ConaxJsonServiceImpl() {
        this.log = LoggerFactory.getLogger(ConaxJsonServiceImpl.class);
    }

    @Override
    public void process(ImmutableConaxRequest request) {
        log.info(LogHelper.CALLING_RESTSERVICE_STARTING);

        conaxControllerApi.postConaxUsingPOST(request);

        log.info(LogHelper.CALLING_RESTSERVICE_FINISHED);
    }
}
