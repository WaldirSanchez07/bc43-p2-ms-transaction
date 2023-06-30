package com.nttdata.mstransaction.application.service.impl;

import com.nttdata.mstransaction.application.dto.request.ConsumptionRequest;
import com.nttdata.mstransaction.application.dto.response.ObjectResponse;
import com.nttdata.mstransaction.application.mapper.ConsumptionRequestMapper;
import com.nttdata.mstransaction.application.service.ConsumptionExternalService;
import com.nttdata.mstransaction.domain.service.BalanceService;
import com.nttdata.mstransaction.domain.service.TransactionService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsumptionExternalServiceImpl implements ConsumptionExternalService {

  private final TransactionService transactionService;
  private final BalanceService balanceService;

  @Override
  public Maybe<ObjectResponse> saveConsumption(ConsumptionRequest request) {
    return balanceService
            .findLastBalanceByAccountId(request.getAccountId())
            .filter(balance -> balance >= request.getAmount())
            .flatMap(balance -> transactionService
                    .saveTransaction(ConsumptionRequestMapper.INSTANCE.map(request))
                    .map(obj -> new ObjectResponse(201, "Consumo registrado!", null))
            );
  }

}
