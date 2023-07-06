package com.nttdata.msaccount.infrastructure.dao.repository;

import com.nttdata.msaccount.domain.utils.CountObject;
import com.nttdata.msaccount.infrastructure.dao.entity.PaymentEntity;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PaymentRepositoryRM extends ReactiveMongoRepository<PaymentEntity, String> {

  @Aggregation(value = {
    "{ $lookup: { from: 'accounts', localField: 'accountId', foreignField: '_id', as: 'account' } }",
    "{ $unwind: { path: '$account', preserveNullAndEmptyArrays: false } }",
    "{ $project: { _id: 1, state: 1, 'account.clientId': 1 } }",
    "{ $match: { state: 'Vencido', 'account.clientId': ?0 } }",
    "{ $count: 'count' }"
  })
  Maybe<CountObject> countDebtsByClientId(String clientId);

}
