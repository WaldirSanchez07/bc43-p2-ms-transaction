package com.nttdata.mstransaction.infrastructure.controller;

import com.nttdata.mstransaction.application.dto.response.ObjectResponse;
import com.nttdata.mstransaction.application.service.PaymentExternalService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/transactions")
public class PaymentController {

  private final PaymentExternalService paymentService;

  @PutMapping(value = "/save-payment", params = "id", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Maybe<ObjectResponse> savePayment(@RequestParam String id) {
    return paymentService.savePaymentCredit(id);
  }

}
