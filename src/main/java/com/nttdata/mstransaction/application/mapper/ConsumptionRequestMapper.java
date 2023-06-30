package com.nttdata.mstransaction.application.mapper;

import com.nttdata.mstransaction.application.dto.request.ConsumptionRequest;
import com.nttdata.mstransaction.domain.model.Transaction;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface ConsumptionRequestMapper {

  ConsumptionRequestMapper INSTANCE = Mappers.getMapper(ConsumptionRequestMapper.class);

  default Transaction map(ConsumptionRequest request) {
    return Transaction.builder()
            .type("Consumo")
            .accountId(new ObjectId(request.getAccountId()))
            .description(request.getDescription())
            .amount(request.getAmount())
            .date(LocalDateTime.now())
            .build();
  }

}
