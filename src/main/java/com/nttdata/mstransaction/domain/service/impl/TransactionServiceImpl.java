package com.nttdata.mstransaction.domain.service.impl;

import com.nttdata.mstransaction.domain.model.Transaction;
import com.nttdata.mstransaction.domain.repository.TransactionRepository;
import com.nttdata.mstransaction.domain.service.TransactionService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Maybe<Transaction> saveTransaction(Transaction transaction) {
        return transactionRepository.saveTransaction(transaction);
    }

    @Override
    public Maybe<Long> countTransactions(String accountId, String type, LocalDateTime startOfMonth, LocalDateTime startOfNextMonth) {
        return transactionRepository.countTransactions(accountId, type, startOfMonth, startOfNextMonth);
    }

}
