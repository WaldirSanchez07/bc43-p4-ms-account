package com.nttdata.msaccount.infrastructure.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payments")
public class PaymentEntity {

  @Id
  private String id;
  private String accountId;
  private Double amount;
  private String state; //Por Pagar, Pagado, Vencido
  private LocalDate payAt;

}
