package dev.eder.vehiculos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

// Lo que el cliente VE.
// "descripcion" no existe en la tabla: la arma el mapper.
@Builder
public record VehiculoResponse(

    Long id,
    String placa,
    String descripcion,
    String color,
    BigDecimal precio,
    LocalDateTime fechaRegistro
) {

}
