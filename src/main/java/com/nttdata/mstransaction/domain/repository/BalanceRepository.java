package com.nttdata.mstransaction.domain.repository;

import com.nttdata.mstransaction.domain.model.Balance;
import io.reactivex.rxjava3.core.Maybe;

public interface BalanceRepository {

    Maybe<Double> findLastBalanceByAccountId(String accountId);
    Maybe<Balance> saveBalance(Balance balance);

}
