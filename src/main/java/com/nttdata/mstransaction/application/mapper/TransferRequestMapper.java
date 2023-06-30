package com.nttdata.mstransaction.application.mapper;

import com.nttdata.mstransaction.application.dto.request.TransferRequest;
import com.nttdata.mstransaction.domain.model.Transaction;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface TransferRequestMapper {

  TransferRequestMapper INSTANCE = Mappers.getMapper(TransferRequestMapper.class);

  default Transaction map(TransferRequest transaction) {
    return Transaction.builder()
            .type("Transferencia")
            .accountId(new ObjectId(transaction.getAccountId()))
            .anotherAccountId(new ObjectId(transaction.getAnotherAccountId()))
            .amount(transaction.getAmount())
            .date(LocalDateTime.now())
            .build();
  }

}
