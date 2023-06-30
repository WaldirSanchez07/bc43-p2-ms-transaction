package com.nttdata.mstransaction.infrastructure.mapper;

import com.nttdata.mstransaction.domain.model.Balance;
import com.nttdata.mstransaction.infrastructure.dao.entity.BalanceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BalanceMapper {

    BalanceMapper INSTANCE = Mappers.getMapper(BalanceMapper.class);

    default Balance map(BalanceEntity balance) {
        return Balance.builder()
                .id(balance.getId())
                .accountId(balance.getAccountId())
                .amount(balance.getAmount())
                .date(balance.getDate())
                .build();
    }

    default BalanceEntity map(Balance balance) {
        return BalanceEntity.builder()
                .id(balance.getId())
                .accountId(balance.getAccountId())
                .amount(balance.getAmount())
                .date(balance.getDate())
                .build();
    }

}
