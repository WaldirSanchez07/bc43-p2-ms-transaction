package com.nttdata.mstransaction.domain.repository;

import com.nttdata.mstransaction.domain.model.Account;
import io.reactivex.rxjava3.core.Maybe;

public interface AccountRepository {

    Maybe<Account> findAccountById(String id);

}
