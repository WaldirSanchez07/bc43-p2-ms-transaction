package com.nttdata.mstransaction.application.mapper;

import com.nttdata.mstransaction.application.dto.request.PaymentRequest;
import com.nttdata.mstransaction.domain.model.Payment;
import io.reactivex.rxjava3.core.Flowable;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface PaymentRequestMapper {

  PaymentRequestMapper INSTANCE = Mappers.getMapper(PaymentRequestMapper.class);

  default Payment map(PaymentRequest request) {
    return Payment.builder()
            .accountId(new ObjectId(request.getAccountId()))
            .amount(request.getAmount())
            .payAt(request.getPayAt())
            .build();
  }

  default List<Payment> map(List<PaymentRequest> request) {
    return request.stream().map(this::map).collect(Collectors.toList());
  }

}
