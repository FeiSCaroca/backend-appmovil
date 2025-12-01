package com.backend.appmovil1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.appmovil1.dto.UserDto;
import com.backend.appmovil1.model.User;
import com.backend.appmovil1.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@Tag(name = "Usuarios", description = "Endpoints para gestión de usuarios en la app")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Registrar usuario",
            description = "Crea un usuario en la base de datos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario creado",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PostMapping
    public User crearUsuario(@Valid @RequestBody UserDto dto) {
    return userService.crearUsuario(dto);
    }

    @Operation(
            summary = "Listar usuarios",
            description = "Obtiene todos los usuarios registrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista devuelta correctamente"),
            }
    )
    @GetMapping
    public List<User> obtenerUsuarios() {
        return userService.obtenerTodos();
    }

    @Operation(
            summary = "Actualizar usuario",
            description = "Actualiza un usuario existente por su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario actualizado",
                            content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @PutMapping("/{id}")
    public User actualizarUsuario(@PathVariable Long id, @Valid @RequestBody UserDto dto) {
        return userService.actualizarUsuario(id, dto);
    }

    @Operation(
            summary = "Eliminar usuario",
            description = "Elimina un usuario por su ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuario eliminado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        // Asumiendo que el servicio lanza una excepción si no lo encuentra
        // y un advice global la maneja para devolver 404.
        // Si el servicio devuelve un booleano, la lógica cambiaría.
        userService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
