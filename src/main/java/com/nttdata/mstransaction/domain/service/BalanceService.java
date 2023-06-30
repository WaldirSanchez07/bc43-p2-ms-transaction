package com.nttdata.mstransaction.domain.service;

import com.nttdata.mstransaction.domain.model.Balance;
import io.reactivex.rxjava3.core.Maybe;

public interface BalanceService {

    Maybe<Double> findLastBalanceByAccountId(String accountId);
    Maybe<Balance> saveBalance(Balance balance);

}
