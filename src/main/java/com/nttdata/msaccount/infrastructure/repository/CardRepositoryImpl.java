package com.nttdata.msaccount.infrastructure.repository;

import com.nttdata.msaccount.domain.model.Card;
import com.nttdata.msaccount.domain.repository.CardRepository;
import com.nttdata.msaccount.infrastructure.dao.repository.CardRepositoryRM;
import com.nttdata.msaccount.infrastructure.mapper.CardMapper;
import io.reactivex.rxjava3.core.Single;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class CardRepositoryImpl implements CardRepository {

  private final CardRepositoryRM repository;

  @Override
  public Single<Card> save(Card card) {
    Mono<Card> mono = repository
            .save(CardMapper.INSTANCE.map(card))
            .map(CardMapper.INSTANCE::map);
    return Single.fromPublisher(mono);
  }

  @Override
  public Single<Card> findById(String id) {
    return repository.customFindById(id).map(CardMapper.INSTANCE::map);
  }

}
