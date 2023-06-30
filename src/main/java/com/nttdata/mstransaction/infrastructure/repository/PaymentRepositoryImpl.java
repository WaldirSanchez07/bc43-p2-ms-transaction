package com.nttdata.mstransaction.infrastructure.repository;

import com.nttdata.mstransaction.domain.model.Payment;
import com.nttdata.mstransaction.domain.repository.PaymentRepository;
import com.nttdata.mstransaction.infrastructure.dao.repository.PaymentRepositoryRM;
import com.nttdata.mstransaction.infrastructure.mapper.PaymentMapper;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

    private final PaymentRepositoryRM paymentRepository;

    @Override
    public Maybe<Payment> savePayment(Payment payment) {
        return Maybe
                .fromPublisher(paymentRepository
                        .save(PaymentMapper.INSTANCE.map(payment))
                        .map(PaymentMapper.INSTANCE::map));
    }

    @Override
    public Maybe<Payment> findById(String id) {
        return paymentRepository
                .customFindById(new ObjectId(id))
                .map(PaymentMapper.INSTANCE::map);
    }

}
