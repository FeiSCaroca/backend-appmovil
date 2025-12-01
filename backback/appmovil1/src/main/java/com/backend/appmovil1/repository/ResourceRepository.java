package com.backend.appmovil1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.appmovil1.model.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
