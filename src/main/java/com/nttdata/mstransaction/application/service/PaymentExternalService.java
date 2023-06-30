package com.nttdata.mstransaction.application.service;

import com.nttdata.mstransaction.application.dto.request.PaymentRequest;
import com.nttdata.mstransaction.application.dto.response.ObjectResponse;
import io.reactivex.rxjava3.core.Maybe;

public interface PaymentExternalService {

  Maybe<ObjectResponse> savePaymentCredit(String paymentId);

}
