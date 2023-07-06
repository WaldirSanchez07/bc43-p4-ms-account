package com.nttdata.msaccount.infrastructure.repository;

import com.nttdata.msaccount.domain.model.Account;
import com.nttdata.msaccount.domain.repository.AccountRepository;
import com.nttdata.msaccount.infrastructure.dao.entity.AccountEntity;
import com.nttdata.msaccount.infrastructure.dao.repository.AccountRepositoryRM;
import com.nttdata.msaccount.infrastructure.mapper.AccountMapper;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

  private final AccountRepositoryRM repository;

  @Override
  public Maybe<Account> save(Account account) {
    Mono<AccountEntity> mono = repository
            .save(AccountMapper.INSTANCE.map(account));
    return Maybe.fromPublisher(AccountMapper.INSTANCE.map(mono));
  }

  @Override
  public Maybe<Account> findById(String id) {
    return repository.customFindById(id)
            .map(AccountMapper.INSTANCE::map);
  }

  @Override
  public Maybe<Long> countByClientIdAndPassiveProductId(String clientId, String passiveProductId) {
    Mono<Long> mono = repository
            .countByClientIdAndPassiveProductId(clientId, passiveProductId)
            .defaultIfEmpty(0L);
    return Maybe.fromPublisher(mono);
  }

  @Override
  public Maybe<Long> countByClientIdAndActiveProductId(String clientId, String activeProductId) {
    Mono<Long> mono = repository
            .countByClientIdAndActiveProductId(clientId, activeProductId)
            .defaultIfEmpty(0L);
    return Maybe.fromPublisher(mono);
  }

  @Override
  public Maybe<Long> countByClientIdAndDifferentCurrentAccount(String clientId) {
    Mono<Long> mono = repository
            .countByClientIdAndPassiveProductIdNot(clientId)
            .defaultIfEmpty(0L);
    return Maybe.fromPublisher(mono);
  }

}
