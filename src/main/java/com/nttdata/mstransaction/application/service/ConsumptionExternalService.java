package com.nttdata.mstransaction.application.service;

import com.nttdata.mstransaction.application.dto.request.ConsumptionRequest;
import com.nttdata.mstransaction.application.dto.response.ObjectResponse;
import io.reactivex.rxjava3.core.Maybe;

public interface ConsumptionExternalService {

  Maybe<ObjectResponse> saveConsumption(ConsumptionRequest request);

}
