package com.nttdata.mstransaction.infrastructure.dao.repository;

import com.nttdata.mstransaction.infrastructure.dao.entity.AccountEntity;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AccountRepositoryRM extends ReactiveMongoRepository<AccountEntity, String> {

    @Query("{ '_id': ?0 }")
    Maybe<AccountEntity> findAccountById(String id);

}
