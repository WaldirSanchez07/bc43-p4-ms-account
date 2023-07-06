package com.nttdata.msaccount.domain.service.impl;

import com.nttdata.msaccount.domain.model.Card;
import com.nttdata.msaccount.domain.repository.CardRepository;
import com.nttdata.msaccount.domain.service.CardService;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

  private final CardRepository repository;

  @Override
  public Single<Card> save(Card card) {
    return repository.save(card);
  }

  @Override
  public Single<Card> findById(String id) {
    return repository.findById(id);
  }

}
