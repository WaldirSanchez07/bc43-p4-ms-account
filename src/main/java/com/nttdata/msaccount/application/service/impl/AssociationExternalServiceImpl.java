package com.nttdata.msaccount.application.service.impl;

import com.nttdata.msaccount.application.dto.request.AssociateRequest;
import com.nttdata.msaccount.application.dto.response.ObjectResponse;
import com.nttdata.msaccount.application.service.AssociationExternalService;
import com.nttdata.msaccount.domain.service.AccountService;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AssociationExternalServiceImpl implements AssociationExternalService {

  private final AccountService accountService;

  @Override
  public Maybe<ObjectResponse> associateAccounts(AssociateRequest request) {
    Flowable<String> accounts = Flowable.fromIterable(request.getAccounts());

    return accounts
            .concatMapMaybe(accountId -> accountService
                    .findById(accountId)
                    .flatMap(obj -> {
                      obj.setCardId(request.getCardId());
                      obj.setAssociationDate(LocalDateTime.now());
                      return accountService.save(obj)
                              .map(o -> new ObjectResponse(200, "Tarjeta asociada!", null));
                    }))
            .lastElement()
            .onErrorResumeNext(error -> Maybe.error(new Throwable(error.getMessage())));
  }

}
