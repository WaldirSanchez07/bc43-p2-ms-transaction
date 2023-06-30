package com.nttdata.mstransaction.infrastructure.controller;

import com.nttdata.mstransaction.application.dto.request.DepositRequest;
import com.nttdata.mstransaction.application.dto.request.TransferRequest;
import com.nttdata.mstransaction.application.dto.request.WithdrawalRequest;
import com.nttdata.mstransaction.application.dto.response.ObjectResponse;
import com.nttdata.mstransaction.application.service.TransactionExternalService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("api/transactions")
public class TransactionController {

    private final TransactionExternalService transactionService;

    @PostMapping(value = "/passive-product/deposit", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Maybe<ObjectResponse> saveDeposit(@Valid @RequestBody DepositRequest depositRequest) {
        return transactionService.saveDeposit(depositRequest);
    }

    @PostMapping(value = "/passive-product/withdrawal", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Maybe<ObjectResponse> saveWithdrawal(@Valid @RequestBody WithdrawalRequest withdrawalRequest) {
        return transactionService.saveWithdrawal(withdrawalRequest);
    }

    @PostMapping(value = "/passive-product/transfer", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Maybe<ObjectResponse> saveTransfer(@Valid @RequestBody TransferRequest transferRequest) {
        return transactionService.saveTransfer(transferRequest);
    }

}
