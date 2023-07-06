package com.nttdata.msaccount.domain.model;

import java.time.LocalDate;
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
public class Payment {

  private String id;
  private String accountId;
  private Double amount;
  private String state;
  private LocalDate payAt;

}
