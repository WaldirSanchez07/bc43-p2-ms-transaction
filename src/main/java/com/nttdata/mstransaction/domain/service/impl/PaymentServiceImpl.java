package com.nttdata.mstransaction.domain.service.impl;

import com.nttdata.mstransaction.domain.model.Payment;
import com.nttdata.mstransaction.domain.repository.PaymentRepository;
import com.nttdata.mstransaction.domain.service.PaymentService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;

  @Override
  public Maybe<Payment> savePayment(Payment payment) {
    return paymentRepository.savePayment(payment);
  }

  @Override
  public Maybe<Payment> findById(String id) {
    return paymentRepository.findById(id);
  }

}
