package com.backend.appmovil1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceDto {

    @NotBlank(message = "La descripción del recurso no puede estar vacía")
    private String descripcion;
}
