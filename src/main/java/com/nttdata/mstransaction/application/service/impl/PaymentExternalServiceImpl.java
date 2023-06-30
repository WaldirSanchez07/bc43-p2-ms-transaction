package com.nttdata.mstransaction.application.service.impl;

import com.nttdata.mstransaction.application.dto.response.ObjectResponse;
import com.nttdata.mstransaction.application.service.PaymentExternalService;
import com.nttdata.mstransaction.domain.service.PaymentService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentExternalServiceImpl implements PaymentExternalService {

  private final PaymentService paymentService;

  @Override
  public Maybe<ObjectResponse> savePaymentCredit(String paymentId) {
    return paymentService.findById(paymentId)
            .flatMap(obj -> {
              obj.setState(1);
              return paymentService.savePayment(obj)
                      .map(o -> new ObjectResponse(200, "Pogo efectuado!", null));
            });
  }

}
