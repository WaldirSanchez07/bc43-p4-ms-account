package com.nttdata.msaccount.utils;

import com.nttdata.msaccount.application.dto.request.CardRequest;
import com.nttdata.msaccount.application.dto.request.PassivePersonalAccountRequest;
import com.nttdata.msaccount.application.dto.response.ObjectResponse;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

import java.time.LocalDate;

public class AccountTestUtil {

  public static String MSJ_BANK_ACCOUNT = "Cuenta bancaria creada!";
  public static String MSJ_CARD = "Tarjeta registrada!";

  public static PassivePersonalAccountRequest requestPassivePersonalAccount() {
    var request = PassivePersonalAccountRequest.builder()
            .accountNumber("20851389345353363143")
            .clientId("1")
            .productId("CB1")
            .build();
    return request;
  }

  public static Maybe<ObjectResponse> responseSaveBankAccount() {
    var obj1 = ObjectResponse.builder()
            .status(201)
            .message(MSJ_BANK_ACCOUNT)
            .data(null)
            .build();

    return Maybe.just(obj1);
  }

  public static CardRequest requestCard() {
    var request = CardRequest.builder()
            .cardNumber("4498818827635448")
            .cardType("Debit")
            .cvv("876")
            .clientId("1")
            .dueDate(LocalDate.now())
            .build();
    return request;
  }

  public static Single<ObjectResponse> responseSaveCard() {
    var obj1 = ObjectResponse.builder()
            .status(201)
            .message(MSJ_CARD)
            .data(null)
            .build();

    return Single.just(obj1);
  }

}
