package com.nttdata.mstransaction.infrastructure.mapper;

import com.nttdata.mstransaction.domain.model.Transaction;
import com.nttdata.mstransaction.infrastructure.dao.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    default Transaction map(TransactionEntity transaction) {
        return Transaction.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .anotherAccountId(transaction.getAnotherAccountId())
                .type(transaction.getType())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .build();
    }

    default TransactionEntity map(Transaction transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .accountId(transaction.getAccountId())
                .anotherAccountId(transaction.getAnotherAccountId())
                .type(transaction.getType())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .build();
    }

}
