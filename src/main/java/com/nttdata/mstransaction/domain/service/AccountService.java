package com.nttdata.mstransaction.domain.service;

import com.nttdata.mstransaction.domain.model.Account;
import io.reactivex.rxjava3.core.Maybe;

public interface AccountService {

    Maybe<Account> findAccountById(String id);

}
