package com.nttdata.msaccount.application.dto.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
public class PassiveEnterpriseAccountRequest {

  @NotEmpty(message = "El campo número de cuenta es requerido")
  @Pattern(regexp = "\\d+", message = "El campo número de cuenta debe ser numérico")
  @Size(min = 20, max = 20, message = "El campo número de cuenta debe tener 20 caracteres")
  private String accountNumber;

  @NotEmpty(message = "El campo cliente es requerido")
  private String clientId;

}
