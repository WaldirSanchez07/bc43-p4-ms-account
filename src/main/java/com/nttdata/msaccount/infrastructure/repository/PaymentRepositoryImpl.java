package com.nttdata.msaccount.infrastructure.repository;

import com.nttdata.msaccount.domain.model.Payment;
import com.nttdata.msaccount.domain.repository.PaymentRepository;
import com.nttdata.msaccount.domain.utils.CountObject;
import com.nttdata.msaccount.infrastructure.dao.repository.PaymentRepositoryRM;
import com.nttdata.msaccount.infrastructure.mapper.PaymentMapper;
import io.reactivex.rxjava3.core.Flowable;
import java.util.List;

import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {

  private final PaymentRepositoryRM repository;

  @Override
  public Flowable<Payment> savePayments(List<Payment> payment, String accountId) {
    return Flowable.fromIterable(payment)
            .flatMap(obj -> {
              obj.setAccountId(accountId);
              return repository
                      .save(PaymentMapper.INSTANCE.map(obj))
                      .map(PaymentMapper.INSTANCE::map);
            });
  }

  @Override
  public Maybe<CountObject> countDebtsByClientId(String clientId) {
    return repository
            .countDebtsByClientId(clientId)
            .defaultIfEmpty(new CountObject(0))
            .toMaybe();
  }

}
