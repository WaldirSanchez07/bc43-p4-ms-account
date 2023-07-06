package com.nttdata.msaccount.application.mapper;

import java.util.UUID;
import com.nttdata.msaccount.application.dto.request.CardRequest;
import com.nttdata.msaccount.domain.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardRequestMapper {

  CardRequestMapper INSTANCE = Mappers.getMapper(CardRequestMapper.class);

  default Card map(CardRequest request) {
    return Card.builder()
            .id(UUID.randomUUID().toString())
            .cardType(request.getCardType())
            .clientId(request.getClientId())
            .cardNumber(request.getCardNumber())
            .cvv(request.getCvv())
            .dueDate(request.getDueDate())
            .build();
  }

}
