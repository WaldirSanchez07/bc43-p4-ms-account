package com.nttdata.msaccount.domain.repository;

import com.nttdata.msaccount.domain.model.Payment;
import com.nttdata.msaccount.domain.utils.CountObject;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

import java.util.List;

public interface PaymentRepository {

    Flowable<Payment> savePayments(List<Payment> payment, String accountId);

    Maybe<CountObject> countDebtsByClientId(String clientId);

}
