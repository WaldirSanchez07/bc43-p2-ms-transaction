package com.nttdata.mstransaction.domain.service;

import com.nttdata.mstransaction.domain.model.Payment;
import io.reactivex.rxjava3.core.Maybe;

public interface PaymentService {

    Maybe<Payment> savePayment(Payment payment);

    Maybe<Payment> findById(String id);

}
