package com.nttdata.mstransaction.application.mapper;

import com.nttdata.mstransaction.application.dto.request.DepositRequest;
import com.nttdata.mstransaction.domain.model.Transaction;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface DepositRequestMapper {

    DepositRequestMapper INSTANCE = Mappers.getMapper(DepositRequestMapper.class);

    default Transaction map(DepositRequest transaction) {
        return Transaction.builder()
                .type("Deposito")
                .accountId(new ObjectId(transaction.getAccountId()))
                .amount(transaction.getAmount())
                .date(LocalDateTime.now())
                .build();
    }

}
