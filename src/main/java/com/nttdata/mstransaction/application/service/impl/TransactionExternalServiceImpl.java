package com.nttdata.mstransaction.application.service.impl;

import com.nttdata.mstransaction.application.dto.request.DepositRequest;
import com.nttdata.mstransaction.application.dto.request.TransferRequest;
import com.nttdata.mstransaction.application.dto.request.WithdrawalRequest;
import com.nttdata.mstransaction.application.dto.response.ObjectResponse;
import com.nttdata.mstransaction.application.mapper.DepositRequestMapper;
import com.nttdata.mstransaction.application.mapper.TransferRequestMapper;
import com.nttdata.mstransaction.application.mapper.WithdrawalRequestMapper;
import com.nttdata.mstransaction.application.service.TransactionExternalService;
import com.nttdata.mstransaction.domain.model.Balance;
import com.nttdata.mstransaction.domain.model.PassiveProduct;
import com.nttdata.mstransaction.domain.model.Transaction;
import com.nttdata.mstransaction.domain.service.AccountService;
import com.nttdata.mstransaction.domain.service.BalanceService;
import com.nttdata.mstransaction.domain.service.PassiveProductService;
import com.nttdata.mstransaction.domain.service.TransactionService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;

@Service
@AllArgsConstructor
public class TransactionExternalServiceImpl implements TransactionExternalService {

  private final TransactionService transactionService;
  private final BalanceService balanceService;
  private final AccountService accountService;
  private final PassiveProductService passiveProductService;

  @Override
  public Maybe<ObjectResponse> saveDeposit(DepositRequest request) {
    YearMonth currentYearMonth = YearMonth.now();
    LocalDateTime startOfMonth = currentYearMonth.atDay(1).atStartOfDay();

    YearMonth nextYearMonth = currentYearMonth.plusMonths(1);
    LocalDateTime startOfNextMonth = nextYearMonth.atDay(1).atStartOfDay();

    return accountService
            .findAccountById(request.getAccountId())
            .flatMap(account -> {
              Maybe<PassiveProduct> productMaybe = passiveProductService
                      .findProductById(account.getPassiveProductId());

              Maybe<Long> transactionsMaybe = transactionService
                      .countTransactions(request.getAccountId(), "Deposito", startOfMonth, startOfNextMonth);

              return productMaybe
                      .flatMap(product -> transactionsMaybe
                              .filter(transactions -> transactions <= product.getMaximumDeposits())
                              .flatMap(count -> (count <= 20)
                                      ? depositWitOutCommission(request)
                                      : depositWitCommission(request, product.getTransactionFee())
                              ));
            });
  }

  @Override
  public Maybe<ObjectResponse> saveWithdrawal(WithdrawalRequest request) {
    YearMonth currentYearMonth = YearMonth.now();
    LocalDateTime startOfMonth = currentYearMonth.atDay(1).atStartOfDay();

    YearMonth nextYearMonth = currentYearMonth.plusMonths(1);
    LocalDateTime startOfNextMonth = nextYearMonth.atDay(1).atStartOfDay();

    return accountService
            .findAccountById(request.getAccountId())
            .flatMap(account -> {
              Maybe<PassiveProduct> productMaybe = passiveProductService
                      .findProductById(account.getPassiveProductId());

              Maybe<Long> transactionsMaybe = transactionService
                      .countTransactions(request.getAccountId(), "Retiro", startOfMonth, startOfNextMonth);

              return productMaybe
                      .flatMap(product -> transactionsMaybe
                              .filter(transactions -> transactions <= product.getMaximumWithdrawals())
                              .flatMap(count -> (count <= 20)
                                      ? withdrawalWithOutCommission(request)
                                      : withdrawalWithCommission(request, product.getTransactionFee())
                              ));
            });
  }

  @Override
  public Maybe<ObjectResponse> saveTransfer(TransferRequest request) {
    return accountService
            .findAccountById(request.getAccountId())
            .flatMap(account -> balanceService
                    .findLastBalanceByAccountId(request.getAccountId())
                    .filter(balance -> balance >= request.getAmount())
                    .flatMap(balance -> {
                      Double newAmount = balance - request.getAmount();

                      Maybe<Transaction> transactionM = transactionService
                              .saveTransaction(TransferRequestMapper.INSTANCE.map(request));

                      Balance balanceAccount1 = Balance.builder()
                              .accountId(new ObjectId(request.getAccountId()))
                              .amount(newAmount)
                              .date(LocalDateTime.now())
                              .build();
                      Maybe<Balance> balanceM1 = balanceService.saveBalance(balanceAccount1);

                      Balance balanceAccount2 = Balance.builder()
                              .accountId(new ObjectId(request.getAnotherAccountId()))
                              .amount(request.getAmount())
                              .date(LocalDateTime.now())
                              .build();
                      Maybe<Balance> balanceM2 = balanceService.saveBalance(balanceAccount2);

                      return Maybe.zip(transactionM, balanceM1, balanceM2, (t, b1, b2) ->
                              new ObjectResponse(201, "Transferencia exitosa!", null)
                      );
                    }));
  }

  /**
   * Guarda un depósito sin comisión
   *
   * @param depositRequest
   * @return
   */
  private Maybe<ObjectResponse> depositWitOutCommission(DepositRequest depositRequest) {
    return balanceService
            .findLastBalanceByAccountId(depositRequest.getAccountId())
            .flatMap(balance -> {
              Double newAmount = balance + depositRequest.getAmount();

              Maybe<Transaction> transactionMaybe = transactionService
                      .saveTransaction(DepositRequestMapper.INSTANCE.map(depositRequest));

              Balance objBalance = Balance.builder()
                      .accountId(new ObjectId(depositRequest.getAccountId()))
                      .amount(newAmount)
                      .date(LocalDateTime.now())
                      .build();
              Maybe<Balance> balanceMaybe = balanceService.saveBalance(objBalance);

              return transactionMaybe.flatMap(obj -> balanceMaybe
                      .map(obj2 -> new ObjectResponse(201, "Transacción exitosa!", null)));
            });
  }

  /**
   * Guarda un depósito con comisión
   *
   * @param request
   * @param commission
   * @return
   */
  private Maybe<ObjectResponse> depositWitCommission(DepositRequest request, Double commission) {
    Maybe<Double> balanceMaybe = balanceService
            .findLastBalanceByAccountId(request.getAccountId());

    Maybe<Transaction> depositM = transactionService
            .saveTransaction(DepositRequestMapper.INSTANCE.map(request));

    Transaction commissionTransact = Transaction.builder()
            .accountId(new ObjectId(request.getAccountId()))
            .type("Comision")
            .amount(commission)
            .date(LocalDateTime.now())
            .build();
    Maybe<Transaction> commissionM = transactionService
            .saveTransaction(commissionTransact);

    Maybe<Balance> balanceM = balanceMaybe.flatMap(balance -> {
      Double newAmount = balance + request.getAmount() - commission;

      Balance objBalance = Balance.builder()
              .accountId(new ObjectId(request.getAccountId()))
              .amount(newAmount)
              .date(LocalDateTime.now())
              .build();
      return balanceService.saveBalance(objBalance);
    });

    return Maybe.zip(depositM, commissionM, balanceM, (d, c, b) ->
            new ObjectResponse(201, "Transacción exitosa!", null));
  }

  /**
   * Guarda el retiro sin comisión
   *
   * @param request
   * @return
   */
  private Maybe<ObjectResponse> withdrawalWithOutCommission(WithdrawalRequest request) {
    return balanceService
            .findLastBalanceByAccountId(request.getAccountId())
            .filter(balance -> balance >= request.getAmount())
            .flatMap(balance -> {
              Double newAmount = balance - request.getAmount();
              Maybe<Transaction> transactionMaybe = transactionService
                      .saveTransaction(WithdrawalRequestMapper.INSTANCE.map(request));

              Balance objBalance = Balance.builder()
                      .accountId(new ObjectId(request.getAccountId()))
                      .amount(newAmount)
                      .date(LocalDateTime.now())
                      .build();
              Maybe<Balance> balanceMaybe = balanceService.saveBalance(objBalance);

              return transactionMaybe.flatMap(obj -> balanceMaybe
                      .map(obj2 -> new ObjectResponse(201, "Transacción exitosa!", null)));
            });
  }

  /**
   * Guarda el retiro con comisión
   *
   * @param request
   * @param commission
   * @return
   */
  private Maybe<ObjectResponse> withdrawalWithCommission(WithdrawalRequest request, Double commission) {
    return balanceService
            .findLastBalanceByAccountId(request.getAccountId())
            .filter(balance -> balance >= request.getAmount() - commission)
            .flatMap(balance -> {
              Double newAmount = balance - request.getAmount() - commission;

              Maybe<Transaction> withdrawalM = transactionService.saveTransaction(WithdrawalRequestMapper.INSTANCE.map(request));

              Transaction commissionTransact = Transaction.builder()
                      .accountId(new ObjectId(request.getAccountId()))
                      .type("Comision")
                      .amount(commission)
                      .date(LocalDateTime.now())
                      .build();
              Maybe<Transaction> commissionM = transactionService.saveTransaction(commissionTransact);

              Balance objBalance = Balance.builder()
                      .accountId(new ObjectId(request.getAccountId()))
                      .amount(newAmount)
                      .date(LocalDateTime.now())
                      .build();
              Maybe<Balance> balanceM = balanceService.saveBalance(objBalance);

              return Maybe.zip(withdrawalM, commissionM, balanceM, (w, c, b) ->
                      new ObjectResponse(201, "Transacción exitosa!", null));
            });
  }

}