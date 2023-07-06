package com.nttdata.msaccount.application.dto.request;

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
public class CardRequest {

  private String clientId;
  private String cardType; //Debit, Credit
  private String cardNumber;
  private LocalDate dueDate;
  private String cvv;

}
