package com.nttdata.msaccount.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

  private String id;
  private String accountId;
  private Double amount;
  private LocalDateTime date;

}
