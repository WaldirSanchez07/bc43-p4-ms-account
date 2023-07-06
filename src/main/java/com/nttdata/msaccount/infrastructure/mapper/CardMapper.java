package com.nttdata.msaccount.infrastructure.mapper;

import com.nttdata.msaccount.domain.model.Card;
import com.nttdata.msaccount.infrastructure.dao.entity.CardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {

  CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

  default CardEntity map(Card card) {
    return CardEntity.builder()
            .id(card.getId())
            .cardType(card.getCardType())
            .clientId(card.getClientId())
            .cardNumber(card.getCardNumber())
            .cvv(card.getCvv())
            .dueDate(card.getDueDate())
            .mainAccountId(card.getMainAccountId())
            .build();
  }

  default Card map(CardEntity card) {
    return Card.builder()
            .id(card.getId())
            .cardType(card.getCardType())
            .clientId(card.getClientId())
            .cardNumber(card.getCardNumber())
            .cvv(card.getCvv())
            .dueDate(card.getDueDate())
            .mainAccountId(card.getMainAccountId())
            .build();
  }

}
