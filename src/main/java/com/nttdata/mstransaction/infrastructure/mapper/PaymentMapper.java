package com.nttdata.mstransaction.infrastructure.mapper;

import com.nttdata.mstransaction.domain.model.Payment;
import com.nttdata.mstransaction.infrastructure.dao.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    default Payment map(PaymentEntity payment) {
        return Payment.builder()
                .id(payment.getId())
                .accountId(payment.getAccountId())
                .amount(payment.getAmount())
                .state(payment.getState())
                .payAt(payment.getPayAt())
                .paidAt(payment.getPaidAt())
                .build();
    }

    default PaymentEntity map(Payment payment) {
        return PaymentEntity.builder()
                .id(payment.getId())
                .accountId(payment.getAccountId())
                .amount(payment.getAmount())
                .state(payment.getState())
                .payAt(payment.getPayAt())
                .paidAt(payment.getPaidAt())
                .build();
    }

}
