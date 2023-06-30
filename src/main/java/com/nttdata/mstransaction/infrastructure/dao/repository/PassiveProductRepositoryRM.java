package com.nttdata.mstransaction.infrastructure.dao.repository;

import com.nttdata.mstransaction.infrastructure.dao.entity.PassiveProductEntity;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PassiveProductRepositoryRM extends ReactiveMongoRepository<PassiveProductEntity, String> {

    @Query("{ '_id': ?0 }")
    Maybe<PassiveProductEntity> findProductById(String id);

}
