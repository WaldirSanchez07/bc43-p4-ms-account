package com.nttdata.msaccount.controller;

import com.nttdata.msaccount.application.dto.request.CardRequest;
import com.nttdata.msaccount.application.dto.request.PassivePersonalAccountRequest;
import com.nttdata.msaccount.application.dto.response.ObjectResponse;
import com.nttdata.msaccount.application.service.AssociationExternalService;
import com.nttdata.msaccount.application.service.BankAccountExternalService;
import com.nttdata.msaccount.application.service.CardExternalService;
import com.nttdata.msaccount.application.service.CreditAccountExternalService;
import com.nttdata.msaccount.infrastructure.controller.AccountController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.nttdata.msaccount.utils.AccountTestUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(AccountControllerTest.class)
public class AccountControllerTest {

  @InjectMocks
  AccountController accountController;

  @Mock
  private BankAccountExternalService bankAccountService;

  @Mock
  private CardExternalService cardService;

  @Test
  public void testSaveBankAccount() {
    when(bankAccountService.saveAccount(any(PassivePersonalAccountRequest.class)))
            .thenReturn(responseSaveBankAccount());

    WebTestClient client = WebTestClient.bindToController(accountController).build();

    client.post()
            .uri("/api/accounts/personal-bank-account/create")
            .contentType(MediaType.APPLICATION_JSON)
            .syncBody(requestPassivePersonalAccount())
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(ObjectResponse.class)
            .consumeWith(o -> {
              Assertions.assertEquals(o.getResponseBody().blockFirst().getStatus(), 201);
            });
  }

  @Test
  public void testSaveCard() {
    when(cardService.saveCard(any(CardRequest.class)))
            .thenReturn(responseSaveCard());

    WebTestClient client = WebTestClient.bindToController(accountController).build();

    client.post()
            .uri("/api/accounts/card/create")
            .contentType(MediaType.APPLICATION_JSON)
            .syncBody(requestCard())
            .exchange()
            .expectStatus()
            .isOk()
            .returnResult(ObjectResponse.class)
            .consumeWith(o -> {
              Assertions.assertEquals(o.getResponseBody().blockFirst().getStatus(), 201);
            });
  }


}
