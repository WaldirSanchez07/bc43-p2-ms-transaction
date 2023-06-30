package com.nttdata.mstransaction.application.service;

import com.nttdata.mstransaction.application.dto.request.DepositRequest;
import com.nttdata.mstransaction.application.dto.request.TransferRequest;
import com.nttdata.mstransaction.application.dto.request.WithdrawalRequest;
import com.nttdata.mstransaction.application.dto.response.ObjectResponse;
import io.reactivex.rxjava3.core.Maybe;

public interface TransactionExternalService {

    Maybe<ObjectResponse> saveDeposit(DepositRequest depositRequest);
    Maybe<ObjectResponse> saveWithdrawal(WithdrawalRequest withdrawalRequest);
    Maybe<ObjectResponse> saveTransfer(TransferRequest request);

}
