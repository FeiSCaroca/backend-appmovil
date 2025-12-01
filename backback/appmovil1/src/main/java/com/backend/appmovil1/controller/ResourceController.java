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

import com.backend.appmovil1.dto.ResourceDto;
import com.backend.appmovil1.model.Resource;
import com.backend.appmovil1.service.ResourceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/resources")
@CrossOrigin("*")
@Tag(name = "Recursos", description = "Endpoints de recursos de la app (consejos, actividades)")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Operation(
            summary = "Crear recurso",
            description = "Crea un recurso (por ejemplo, un consejo de apoyo emocional)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso creado",
                            content = @Content(schema = @Schema(implementation = Resource.class)))
            }
    )
    @PostMapping
    public Resource crearResource(@Valid @RequestBody ResourceDto dto) {
    return resourceService.crearRecurso(dto);
    }

    @Operation(
            summary = "Listar recursos",
            description = "Devuelve todos los recursos registrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
            }
    )
    @GetMapping
    public List<Resource> obtenerRecursos() {
        return resourceService.obtenerTodos();
    }

    @Operation(
            summary = "Actualizar recurso",
            description = "Actualiza un recurso existente por su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso actualizado",
                            content = @Content(schema = @Schema(implementation = Resource.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
            }
    )
    @PutMapping("/{id}")
    public Resource actualizarRecurso(@PathVariable Long id, @Valid @RequestBody ResourceDto dto) {
        return resourceService.actualizarRecurso(id, dto);
    }

    @Operation(
            summary = "Eliminar recurso",
            description = "Elimina un recurso por su ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Recurso eliminado"),
                    @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRecurso(@PathVariable Long id) {
        // Asumiendo que el servicio lanza una excepción si no lo encuentra
        // y un advice global la maneja para devolver 404.
        // Si el servicio devuelve un booleano, la lógica cambiaría.
        resourceService.eliminarRecurso(id);
        return ResponseEntity.noContent().build();
    }
}
