package com.gepardec.sypoc.service.api;

import com.gepardec.sypoc.swagger.client.conax.model.ImmutableConaxRequest;

public interface ConaxJsonService {

	void process(ImmutableConaxRequest request);
}
