package com.nttdata.mstransaction.domain.service.impl;

import com.nttdata.mstransaction.domain.model.PassiveProduct;
import com.nttdata.mstransaction.domain.repository.PassiveProductRepository;
import com.nttdata.mstransaction.domain.service.PassiveProductService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PassiveProductServiceImpl implements PassiveProductService {

    private final PassiveProductRepository passiveProductRepository;

    @Override
    public Maybe<PassiveProduct> findProductById(String id) {
        return passiveProductRepository.findProductById(id);
    }

    @Override
    public Maybe<PassiveProduct> transactionLimit(String clientId) {
        return passiveProductRepository.transactionLimit(clientId);
    }

}
