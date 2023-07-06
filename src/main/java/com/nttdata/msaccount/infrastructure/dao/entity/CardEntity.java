package com.nttdata.msaccount.infrastructure.dao.entity;

import java.time.LocalDate;
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
@Document(collection = "cards")
public class CardEntity {

  @Id
  private String id;
  private String clientId;
  private String cardType; //Debit, Credit
  private String cardNumber;
  private LocalDate dueDate;
  private String cvv;

  private String mainAccountId;

}
