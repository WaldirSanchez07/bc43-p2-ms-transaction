package com.nttdata.mstransaction.domain.service;

import com.nttdata.mstransaction.domain.model.PassiveProduct;
import io.reactivex.rxjava3.core.Maybe;

public interface PassiveProductService {

    Maybe<PassiveProduct> findProductById(String id);
    Maybe<PassiveProduct> transactionLimit(String clientId);

}
