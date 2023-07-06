package com.nttdata.msaccount.infrastructure.dao.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "balances")
public class BalanceEntity {

  @Id
  private String id;
  private String accountId;
  private Double amount;
  private LocalDateTime date;

}
