package dev.eder.vehiculos.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// Lo que el cliente PUEDE mandar. Sin id ni fechaRegistro.
public record VehiculoRequest(

    @NotBlank(message = "La placa es obligatoria")
    @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "La placa debe de tener el formato ABC-123")
    String placa,

    @NotBlank(message = "La marca es obligatoria")
    @Size(max = 50, message = "La marca no puede tener maximo de 50 caracteres")
    String marca,

    @NotBlank(message = "El modelo es obligatorio")
    @Size(max = 50, message = "El modelo no puede tener maximo de 50 caracteres")
    String modelo,

    @NotNull(message = "el año es obligatorio")
    @Min(value = 1990, message = "El año minimo es de 1990")
    @Max(value = 2027, message = "El año maximo es de 2027")
    Integer anio,

    @NotBlank(message = "El color es obligatorio")
    @Size(max = 30, message = "El color no puede tener mas de 30 caracteres")
    String color,

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe de ser mayor que 0")
    BigDecimal precio
) {

}
