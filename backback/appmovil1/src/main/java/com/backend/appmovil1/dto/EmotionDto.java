package com.backend.appmovil1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmotionDto {

    @NotBlank(message = "El nombreUsuario no puede estar vacío")
    private String nombreUsuario;

    @NotBlank(message = "El texto de la emoción no puede estar vacío")
    private String emocionTexto;
}
