package com.nttdata.mstransaction.domain.repository;

import com.nttdata.mstransaction.domain.model.Transaction;
import io.reactivex.rxjava3.core.Maybe;

import java.time.LocalDateTime;

public interface TransactionRepository {

    Maybe<Transaction> saveTransaction(Transaction transaction);

    Maybe<Long> countTransactions(String accountId, String type, LocalDateTime startOfMonth, LocalDateTime startOfNextMonth);

}
