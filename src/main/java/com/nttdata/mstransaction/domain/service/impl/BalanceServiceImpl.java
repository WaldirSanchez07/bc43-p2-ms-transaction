package com.nttdata.mstransaction.domain.service.impl;

import com.nttdata.mstransaction.domain.model.Balance;
import com.nttdata.mstransaction.domain.repository.BalanceRepository;
import com.nttdata.mstransaction.domain.service.BalanceService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    @Override
    public Maybe<Double> findLastBalanceByAccountId(String accountId) {
        return balanceRepository.findLastBalanceByAccountId(accountId);
    }

    @Override
    public Maybe<Balance> saveBalance(Balance balance) {
        return balanceRepository.saveBalance(balance);
    }

}
