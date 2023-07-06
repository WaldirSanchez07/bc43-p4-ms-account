package com.nttdata.msaccount.application.service.impl;

import com.nttdata.msaccount.application.dto.request.CardRequest;
import com.nttdata.msaccount.application.dto.request.MainAccountRequest;
import com.nttdata.msaccount.application.dto.response.ObjectResponse;
import com.nttdata.msaccount.application.mapper.CardRequestMapper;
import com.nttdata.msaccount.application.service.CardExternalService;
import com.nttdata.msaccount.domain.service.CardService;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardExternalServiceImpl implements CardExternalService {

  private final CardService cardService;

  @Override
  public Single<ObjectResponse> saveCard(CardRequest request) {
    return cardService
            .save(CardRequestMapper.INSTANCE.map(request))
            .map(obj -> new ObjectResponse(201, "Tarjeta registrada!", obj))
            .onErrorResumeNext(error -> Single.error(new Throwable(error.getMessage())));
  }

  @Override
  public Single<ObjectResponse> saveMainAccount(MainAccountRequest request) {
    return cardService.findById(request.getCardId())
            .flatMap(obj -> {
              obj.setMainAccountId(request.getAccountId());
              return cardService.save(obj)
                      .map(card -> new ObjectResponse(201, "Cuenta principal registrada!", card));
            }).onErrorResumeNext(error -> Single.error(new Throwable(error.getMessage())));
  }

}
