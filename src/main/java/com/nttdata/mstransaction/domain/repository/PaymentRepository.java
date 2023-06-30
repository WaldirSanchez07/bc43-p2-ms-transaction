package com.nttdata.mstransaction.domain.repository;

import com.nttdata.mstransaction.domain.model.Payment;
import io.reactivex.rxjava3.core.Maybe;

public interface PaymentRepository {

    Maybe<Payment> savePayment(Payment payment);

    Maybe<Payment> findById(String id);

}
