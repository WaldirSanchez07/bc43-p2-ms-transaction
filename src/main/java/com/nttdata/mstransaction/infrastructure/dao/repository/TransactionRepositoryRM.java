package com.nttdata.mstransaction.infrastructure.dao.repository;

import com.nttdata.mstransaction.infrastructure.dao.entity.TransactionEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TransactionRepositoryRM extends ReactiveMongoRepository<TransactionEntity, String> {

//    @Query("{'accountId': ?0, 'type': ?1, 'date': {'$gte': ?2, '$lt': ?3}}")
    Mono<Long> countByAccountIdAndTypeAndDateBetween(ObjectId accountId, String type, LocalDateTime startOfMonth, LocalDateTime startOfNextMonth);

}
