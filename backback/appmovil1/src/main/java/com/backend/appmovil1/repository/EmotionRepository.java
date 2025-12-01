package com.backend.appmovil1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.appmovil1.model.Emotion;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {
}
