package com.nttdata.mstransaction.infrastructure.mapper;

import com.nttdata.mstransaction.domain.model.PassiveProduct;
import com.nttdata.mstransaction.infrastructure.dao.entity.PassiveProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassiveProductMapper {

    PassiveProductMapper INSTANCE = Mappers.getMapper(PassiveProductMapper.class);

    default PassiveProduct map(PassiveProductEntity product) {
        return PassiveProduct.builder()
                .id(product.getId())
                .name(product.getName())
                .hasMaintenanceFee(product.getHasMaintenanceFee())
                .maximumDeposits(product.getMaximumDeposits())
                .maximumWithdrawals(product.getMaximumWithdrawals())
                .transactionFee(product.getTransactionFee())
                .transactionDay(product.getTransactionDay())
                .build();
    }


}
