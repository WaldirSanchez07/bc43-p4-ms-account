package com.nttdata.msaccount.domain.service.impl;

import com.nttdata.msaccount.domain.model.Payment;
import com.nttdata.msaccount.domain.repository.PaymentRepository;
import com.nttdata.msaccount.domain.service.PaymentService;
import com.nttdata.msaccount.domain.utils.CountObject;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository repository;

  @Override
  public Flowable<Payment> savePayments(List<Payment> payments, String accountId) {
    return repository.savePayments(payments, accountId);
  }

  @Override
  public Maybe<CountObject> countDebtsByClientId(String clientId) {
    return repository.countDebtsByClientId(clientId);
  }

}
