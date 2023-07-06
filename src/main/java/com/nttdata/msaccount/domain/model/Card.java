package com.nttdata.msaccount.domain.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card {

  private String id;
  private String clientId;
  private String cardType; //Debit, Credit
  private String cardNumber;
  private LocalDate dueDate;
  private String cvv;

  private String mainAccountId;

}
