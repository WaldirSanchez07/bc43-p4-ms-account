package com.nttdata.msaccount.application.service.impl;

import com.nttdata.msaccount.application.dto.request.ActiveEnterpriseAccountRequest;
import com.nttdata.msaccount.application.dto.request.ActivePersonalAccountRequest;
import com.nttdata.msaccount.application.dto.response.ObjectResponse;
import com.nttdata.msaccount.application.mapper.AccountRequestMapper;
import com.nttdata.msaccount.application.mapper.PaymentRequestMapper;
import com.nttdata.msaccount.application.service.CreditAccountExternalService;
import com.nttdata.msaccount.domain.model.Balance;
import com.nttdata.msaccount.domain.service.AccountService;
import com.nttdata.msaccount.domain.service.BalanceService;
import com.nttdata.msaccount.domain.service.PaymentService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CreditAccountExternalServiceImpl implements CreditAccountExternalService {

  private final AccountService accountService;
  private final PaymentService paymentService;
  private final BalanceService balanceService;

  @Override
  public Maybe<ObjectResponse> savePersonalCreditAccount(ActivePersonalAccountRequest request) {
    var msj = "Ya tienes una crédito, solo se permite un solo crédito por persona!";

    return accountService
            .countByClientIdAndActiveProductId(request.getClientId(), "CC1")
            .filter(count -> count == 0)
            .flatMap(count -> accountService
                    .save(AccountRequestMapper.INSTANCE.map(request))
                    .flatMap(obj -> {

                      Maybe<ObjectResponse> maybe1 = paymentService
                              .savePayments(PaymentRequestMapper.INSTANCE
                                      .map(request.getPayments()), obj.getId())
                              .map(o -> new ObjectResponse(201, "Ok!", null))
                              .firstElement();

                      var balance = Balance.builder()
                              .id(UUID.randomUUID().toString())
                              .accountId(obj.getId())
                              .amount(request.getAmount())
                              .date(LocalDateTime.now())
                              .build();

                      Maybe<ObjectResponse> maybe2 = balanceService
                              .saveBalance(balance)
                              .map(o -> new ObjectResponse(201, "Ok!", null));

                      return Maybe.zip(maybe1, maybe2, (m1, m2) -> new ObjectResponse(
                              201,
                              "Cuenta bancaria creada!",
                              null));
                    }))
            .switchIfEmpty(Maybe.error(new Throwable(msj)))
            .onErrorResumeNext(error -> Maybe.error(new Throwable(error.getMessage())));

  }

  @Override
  public Maybe<ObjectResponse> saveEnterpriseCreditAccount(ActiveEnterpriseAccountRequest request) {
    var msjDeuda = "No es posible crear una cuenta porque tiene deudas en un producto de crédito!";
    return paymentService
            .countDebtsByClientId(request.getClientId())
            .filter(obj -> obj.getCount() == 0)
            .flatMap(c -> accountService
                    .save(AccountRequestMapper.INSTANCE.map(request))
                    .flatMap(obj -> {
                      Maybe<ObjectResponse> maybe1 = paymentService
                              .savePayments(PaymentRequestMapper.INSTANCE
                                      .map(request.getPayments()), obj.getId())
                              .map(o -> new ObjectResponse(201, "Ok!", null))
                              .firstElement();

                      var balance = Balance.builder()
                              .id(UUID.randomUUID().toString())
                              .accountId(obj.getId())
                              .amount(request.getAmount())
                              .date(LocalDateTime.now())
                              .build();

                      Maybe<ObjectResponse> maybe2 = balanceService
                              .saveBalance(balance)
                              .map(o -> new ObjectResponse(201, "Ok!", null));

                      return Maybe.zip(maybe1, maybe2, (m1, m2) -> new ObjectResponse(
                              201,
                              "Cuenta bancaria creada!",
                              null));
                    }))
            .switchIfEmpty(Maybe.error(new Throwable(msjDeuda)))
            .onErrorResumeNext(error -> Maybe.error(new Throwable(error.getMessage())));
  }

}
