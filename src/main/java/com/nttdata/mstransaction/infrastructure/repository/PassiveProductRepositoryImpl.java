package com.nttdata.mstransaction.infrastructure.repository;

import com.nttdata.mstransaction.domain.model.PassiveProduct;
import com.nttdata.mstransaction.domain.repository.PassiveProductRepository;
import com.nttdata.mstransaction.infrastructure.dao.repository.AccountRepositoryRM;
import com.nttdata.mstransaction.infrastructure.dao.repository.PassiveProductRepositoryRM;
import com.nttdata.mstransaction.infrastructure.mapper.PassiveProductMapper;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PassiveProductRepositoryImpl implements PassiveProductRepository {

    private final PassiveProductRepositoryRM passiveProductRepository;
    private final AccountRepositoryRM accountRepository;

    @Override
    public Maybe<PassiveProduct> findProductById(String id) {
        return passiveProductRepository.findProductById(id).map(PassiveProductMapper.INSTANCE::map);
    }

    @Override
    public Maybe<PassiveProduct> transactionLimit(String clientId) {
        return Maybe.fromPublisher(accountRepository.findById(clientId)
                .flatMap(client -> passiveProductRepository.findById(client.getPassiveProductId())
                        .map(PassiveProductMapper.INSTANCE::map)));
    }

}
