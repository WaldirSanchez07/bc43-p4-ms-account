package com.nttdata.msaccount.infrastructure.mapper;

import com.nttdata.msaccount.domain.model.Payment;
import com.nttdata.msaccount.infrastructure.dao.entity.PaymentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface PaymentMapper {

  PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

  default Payment map(PaymentEntity payment) {
    return Payment.builder()
            .id(payment.getId())
            .accountId(payment.getAccountId())
            .amount(payment.getAmount())
            .state(payment.getState())
            .payAt(payment.getPayAt())
            .build();
  }

  default PaymentEntity map(Payment payment) {
    return PaymentEntity.builder()
            .id(UUID.randomUUID().toString())
            .accountId(payment.getAccountId())
            .amount(payment.getAmount())
            .state(payment.getState())
            .payAt(payment.getPayAt())
            .build();
  }

}
