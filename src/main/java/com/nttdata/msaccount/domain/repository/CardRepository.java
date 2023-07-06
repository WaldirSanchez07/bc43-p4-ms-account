package com.nttdata.msaccount.domain.repository;

import com.nttdata.msaccount.domain.model.Card;
import io.reactivex.rxjava3.core.Single;

public interface CardRepository {

  Single<Card> save(Card card);

  Single<Card> findById(String id);

}
