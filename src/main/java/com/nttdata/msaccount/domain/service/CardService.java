package com.nttdata.msaccount.domain.service;

import com.nttdata.msaccount.domain.model.Card;
import io.reactivex.rxjava3.core.Single;

public interface CardService {

  Single<Card> save(Card card);

  Single<Card> findById(String id);

}
