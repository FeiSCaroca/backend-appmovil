package com.backend.appmovil1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.appmovil1.dto.EmotionDto;
import com.backend.appmovil1.exception.NotFoundException;
import com.backend.appmovil1.model.Emotion;
import com.backend.appmovil1.repository.EmotionRepository;

@Service
public class EmotionService {

    @Autowired
    private EmotionRepository emotionRepository;

    public Emotion crearEmotion(EmotionDto dto) {
        Emotion emotion = Emotion.builder()
                .nombreUsuario(dto.getNombreUsuario())
                .emocionTexto(dto.getEmocionTexto())
                .build();

        return emotionRepository.save(emotion);
    }

    public List<Emotion> obtenerTodas() {
        return emotionRepository.findAll();
    }

    public Emotion actualizarEmotion(Long id, EmotionDto dto) {
        Emotion emotion = emotionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Emoción no encontrada con id: " + id));

        emotion.setNombreUsuario(dto.getNombreUsuario());
        emotion.setEmocionTexto(dto.getEmocionTexto());

        return emotionRepository.save(emotion);
    }

    public void eliminarEmotion(Long id) {
        if (!emotionRepository.existsById(id)) {
            throw new NotFoundException("Emoción no encontrada con id: " + id);
        }
        emotionRepository.deleteById(id);
    }
}
