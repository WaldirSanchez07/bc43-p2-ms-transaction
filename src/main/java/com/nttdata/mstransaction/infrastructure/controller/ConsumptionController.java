package com.nttdata.mstransaction.infrastructure.controller;

import com.nttdata.mstransaction.application.dto.request.ConsumptionRequest;
import com.nttdata.mstransaction.application.dto.response.ObjectResponse;
import com.nttdata.mstransaction.application.service.ConsumptionExternalService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("api/transactions")
public class ConsumptionController {

  private final ConsumptionExternalService consumptionService;

  @PostMapping(value = "/active-product/consumption", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Maybe<ObjectResponse> save(@Valid @RequestBody ConsumptionRequest request) {
    return consumptionService.saveConsumption(request);
  }

}
