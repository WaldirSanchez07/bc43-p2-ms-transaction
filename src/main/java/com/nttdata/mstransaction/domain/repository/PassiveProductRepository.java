package com.nttdata.mstransaction.domain.repository;

import com.nttdata.mstransaction.domain.model.PassiveProduct;
import io.reactivex.rxjava3.core.Maybe;

public interface PassiveProductRepository {

    Maybe<PassiveProduct> findProductById(String id);
    Maybe<PassiveProduct> transactionLimit(String clientId);

}
