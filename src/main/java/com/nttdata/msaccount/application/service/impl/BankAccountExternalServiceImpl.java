package com.nttdata.msaccount.application.service.impl;

import com.nttdata.msaccount.application.dto.request.PassiveEnterpriseAccountRequest;
import com.nttdata.msaccount.application.dto.request.PassivePersonalAccountRequest;
import com.nttdata.msaccount.application.dto.response.ObjectResponse;
import com.nttdata.msaccount.application.mapper.AccountRequestMapper;
import com.nttdata.msaccount.application.service.BankAccountExternalService;
import com.nttdata.msaccount.domain.service.AccountService;
import com.nttdata.msaccount.domain.service.PaymentService;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankAccountExternalServiceImpl implements BankAccountExternalService {

  private final AccountService accountService;
  private final PaymentService paymentService;

  @Override
  public Maybe<ObjectResponse> saveAccount(PassivePersonalAccountRequest request) {
    var msj = "Ya tienes una cuenta de este producto, solo puedes una cuenta de cada producto!";
    var msjDeuda = "No es posible crear una cuenta porque tiene deudas en un producto de crédito!";

    return paymentService
            .countDebtsByClientId(request.getClientId())
            .filter(obj -> obj.getCount() == 0)
            .flatMap(c -> accountService
                    .countByClientIdAndPassiveProductId(request.getClientId(), request.getProductId())
                    .filter(count -> count == 0)
                    .flatMap(count -> accountService
                            .save(AccountRequestMapper.INSTANCE.map(request))
                            .map(obj -> new ObjectResponse(201, "Cuenta bancaria creada!", null)))
                    .switchIfEmpty(Maybe.error(new Throwable(msj))))
            .switchIfEmpty(Maybe.error(new Throwable(msjDeuda)))
            .onErrorResumeNext(error -> Maybe.error(new Throwable(error.getMessage())));
  }

  @Override
  public Maybe<ObjectResponse> saveEnterpriseBankAccount(PassiveEnterpriseAccountRequest request) {
    var msjDeuda = "No es posible crear una cuenta porque tiene deudas en un producto de crédito!";

    return paymentService
            .countDebtsByClientId(request.getClientId())
            .filter(obj -> obj.getCount() == 0)
            .flatMap(c -> accountService
                    .save(AccountRequestMapper.INSTANCE.map(request))
                    .map(obj -> new ObjectResponse(201, "Cuenta bancaria creada!", null)))
            .switchIfEmpty(Maybe.error(new Throwable(msjDeuda)))
            .onErrorResumeNext(error -> Maybe.error(new Throwable(error.getMessage())));
  }

}
