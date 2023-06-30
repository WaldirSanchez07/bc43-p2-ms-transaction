package com.nttdata.mstransaction.infrastructure.mapper;

import com.nttdata.mstransaction.domain.model.Account;
import com.nttdata.mstransaction.infrastructure.dao.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    default Account map(AccountEntity account) {
        return Account.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .clientId(account.getClientId())
                .passiveProductId(account.getPassiveProductId())
                .activeProductId(account.getActiveProductId())
                .build();
    }

}
