package com.nttdata.msaccount.infrastructure.controller;

import com.nttdata.msaccount.application.dto.request.ActiveEnterpriseAccountRequest;
import com.nttdata.msaccount.application.dto.request.ActivePersonalAccountRequest;
import com.nttdata.msaccount.application.dto.request.AssociateRequest;
import com.nttdata.msaccount.application.dto.request.CardRequest;
import com.nttdata.msaccount.application.dto.request.MainAccountRequest;
import com.nttdata.msaccount.application.dto.request.PassiveEnterpriseAccountRequest;
import com.nttdata.msaccount.application.dto.request.PassivePersonalAccountRequest;
import com.nttdata.msaccount.application.dto.response.ObjectResponse;
import com.nttdata.msaccount.application.service.AssociationExternalService;
import com.nttdata.msaccount.application.service.BankAccountExternalService;
import com.nttdata.msaccount.application.service.CardExternalService;
import com.nttdata.msaccount.application.service.CreditAccountExternalService;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Rest Full.
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/accounts")
public class AccountController {

  private final BankAccountExternalService bankAccountService;
  private final CreditAccountExternalService creditAccountService;
  private final CardExternalService cardService;
  private final AssociationExternalService associationService;

  @PostMapping(
          value = "/personal-bank-account/create",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE
  )
  public Maybe<ObjectResponse> saveBankAccount(
          @Valid @RequestBody PassivePersonalAccountRequest request
  ) {
    return bankAccountService.saveAccount(request);
  }

  @PostMapping(
          value = "/personal-credit/create",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE
  )
  public Maybe<ObjectResponse> saveAccountPersonalCredit(
          @Valid @RequestBody ActivePersonalAccountRequest request
  ) {
    return creditAccountService.savePersonalCreditAccount(request);
  }

  @PostMapping(
          value = "/enterprise-credit/create",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE
  )
  public Maybe<ObjectResponse> saveAccountEnterpriseCredit(
          @Valid @RequestBody ActiveEnterpriseAccountRequest request
  ) {
    return creditAccountService.saveEnterpriseCreditAccount(request);
  }

  @PostMapping(
          value = "/enterprise-current/create",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE
  )
  public Maybe<ObjectResponse> saveAccountEnterpriseCurrent(
          @Valid @RequestBody PassiveEnterpriseAccountRequest request
  ) {
    return bankAccountService.saveEnterpriseBankAccount(request);
  }

  @PostMapping(
          value = "/card/create",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE
  )
  public Single<ObjectResponse> saveCard(
          @Valid @RequestBody CardRequest request
  ) {
    return cardService.saveCard(request);
  }

  @PostMapping(
          value = "/card/associate",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE
  )
  public Maybe<ObjectResponse> saveCardAssociate(
          @Valid @RequestBody AssociateRequest request
  ) {
    return associationService.associateAccounts(request);
  }

  @PostMapping(
          value = "/card/main-account",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE
  )
  public Single<ObjectResponse> saveMainAccount(
          @Valid @RequestBody MainAccountRequest request
  ) {
    return cardService.saveMainAccount(request);
  }

}
