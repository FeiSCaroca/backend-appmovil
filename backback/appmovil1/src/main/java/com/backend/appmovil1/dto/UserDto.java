package com.backend.appmovil1.dto;

import lombok.Data;

@Data
public class UserDto {

    private String nombre;
    private String correo;
    private String clave;
    private Boolean aceptaTerminos;

}