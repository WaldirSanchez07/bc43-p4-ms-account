package com.nttdata.msaccount.infrastructure.dao.repository;

import com.nttdata.msaccount.infrastructure.dao.entity.CardEntity;
import io.reactivex.rxjava3.core.Single;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CardRepositoryRM extends ReactiveMongoRepository<CardEntity, String> {

  @Query(value = "{ _id: ?0 }")
  Single<CardEntity> customFindById(String id);

}
