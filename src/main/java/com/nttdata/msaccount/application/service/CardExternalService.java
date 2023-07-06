package com.nttdata.msaccount.application.service;

import com.nttdata.msaccount.application.dto.request.CardRequest;
import com.nttdata.msaccount.application.dto.request.MainAccountRequest;
import com.nttdata.msaccount.application.dto.response.ObjectResponse;
import io.reactivex.rxjava3.core.Single;

public interface CardExternalService {

  Single<ObjectResponse> saveCard(CardRequest request);
  Single<ObjectResponse> saveMainAccount(MainAccountRequest request);

}
