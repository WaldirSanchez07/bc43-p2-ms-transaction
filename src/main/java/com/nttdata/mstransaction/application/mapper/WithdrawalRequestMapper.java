package com.nttdata.mstransaction.application.mapper;

import com.nttdata.mstransaction.application.dto.request.WithdrawalRequest;
import com.nttdata.mstransaction.domain.model.Transaction;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper
public interface WithdrawalRequestMapper {

    WithdrawalRequestMapper INSTANCE = Mappers.getMapper(WithdrawalRequestMapper.class);

    default Transaction map(WithdrawalRequest transaction) {
        return Transaction.builder()
                .type("Retiro")
                .accountId(new ObjectId(transaction.getAccountId()))
                .amount(transaction.getAmount())
                .date(LocalDateTime.now())
                .build();
    }

}
