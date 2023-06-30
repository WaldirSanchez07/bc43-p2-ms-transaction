package com.nttdata.mstransaction.infrastructure.repository;

import com.nttdata.mstransaction.domain.model.Transaction;
import com.nttdata.mstransaction.domain.repository.TransactionRepository;
import com.nttdata.mstransaction.infrastructure.dao.repository.TransactionRepositoryRM;
import com.nttdata.mstransaction.infrastructure.mapper.TransactionMapper;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@AllArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionRepositoryRM transactionRepository;

    @Override
    public Maybe<Transaction> saveTransaction(Transaction transaction) {
        return Maybe.fromPublisher(transactionRepository
                .save(TransactionMapper.INSTANCE.map(transaction))
                .map(TransactionMapper.INSTANCE::map));
    }

    @Override
    public Maybe<Long> countTransactions(String accountId, String type, LocalDateTime startOfMonth, LocalDateTime startOfNextMonth) {
        ObjectId objectId = new ObjectId(accountId);
        return Maybe.fromPublisher(transactionRepository
                .countByAccountIdAndTypeAndDateBetween(objectId, type, startOfMonth, startOfNextMonth)
                .defaultIfEmpty(0L));
    }

}
