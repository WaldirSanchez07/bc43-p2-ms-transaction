package com.nttdata.mstransaction.infrastructure.repository;

import com.nttdata.mstransaction.domain.model.Account;
import com.nttdata.mstransaction.domain.repository.AccountRepository;
import com.nttdata.mstransaction.infrastructure.dao.repository.AccountRepositoryRM;
import com.nttdata.mstransaction.infrastructure.mapper.AccountMapper;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountRepositoryRM accountRepository;

    @Override
    public Maybe<Account> findAccountById(String id) {
        return accountRepository.findAccountById(id).map(AccountMapper.INSTANCE::map);
    }

}
