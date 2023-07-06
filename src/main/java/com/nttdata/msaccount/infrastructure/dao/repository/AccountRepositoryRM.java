package com.nttdata.msaccount.infrastructure.dao.repository;

import com.nttdata.msaccount.infrastructure.dao.entity.AccountEntity;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AccountRepositoryRM extends ReactiveMongoRepository<AccountEntity, String> {

  @Query(value = "{ _id: ?0 }")
  Maybe<AccountEntity> customFindById(String id);

  Mono<Long> countByClientIdAndPassiveProductId(String clientId, String passiveProductId);

  Mono<Long> countByClientIdAndActiveProductId(String clientId, String activeProductId);

  Mono<Long> countByClientIdAndPassiveProductIdNot(String clientId);

}
