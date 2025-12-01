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

import com.backend.appmovil1.dto.EmotionDto;
import com.backend.appmovil1.model.Emotion;
import com.backend.appmovil1.service.EmotionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/emotions")
@CrossOrigin("*")
@Tag(name = "Emociones", description = "Endpoints para registrar y obtener emociones del usuario")
public class EmotionController {

    @Autowired
    private EmotionService emotionService;

    @Operation(
            summary = "Registrar emoción",
            description = "Guarda una emoción ingresada por el usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Emoción creada",
                            content = @Content(schema = @Schema(implementation = Emotion.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
            }
    )
    @PostMapping
    public Emotion crearEmotion(@Valid @RequestBody EmotionDto dto) {
    return emotionService.crearEmotion(dto);
    }

    @Operation(
            summary = "Listar emociones",
            description = "Devuelve todas las emociones guardadas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
            }
    )
    @GetMapping
    public List<Emotion> obtenerEmotions() {
        return emotionService.obtenerTodas();
    }

    @Operation(
            summary = "Actualizar emoción",
            description = "Actualiza una emoción existente por su ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Emoción actualizada",
                            content = @Content(schema = @Schema(implementation = Emotion.class))),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
                    @ApiResponse(responseCode = "404", description = "Emoción no encontrada")
            }
    )
    @PutMapping("/{id}")
    public Emotion actualizarEmotion(@PathVariable Long id, @Valid @RequestBody EmotionDto dto) {
        return emotionService.actualizarEmotion(id, dto);
    }

    @Operation(
            summary = "Eliminar emoción",
            description = "Elimina una emoción por su ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Emoción eliminada"),
                    @ApiResponse(responseCode = "404", description = "Emoción no encontrada")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEmotion(@PathVariable Long id) {
        // Asumiendo que el servicio lanza una excepción si no lo encuentra
        // y un advice global la maneja para devolver 404.
        // Si el servicio devuelve un booleano, la lógica cambiaría.
        emotionService.eliminarEmotion(id);
        return ResponseEntity.noContent().build();
    }
}
