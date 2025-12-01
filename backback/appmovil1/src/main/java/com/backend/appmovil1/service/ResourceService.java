package com.backend.appmovil1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.appmovil1.dto.ResourceDto;
import com.backend.appmovil1.exception.NotFoundException;
import com.backend.appmovil1.model.Resource;
import com.backend.appmovil1.repository.ResourceRepository;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public Resource crearRecurso(ResourceDto dto) {
        Resource resource = Resource.builder()
                .descripcion(dto.getDescripcion())
                .build();

        return resourceRepository.save(resource);
    }

    public List<Resource> obtenerTodos() {
        return resourceRepository.findAll();
    }

    public Resource actualizarRecurso(Long id, ResourceDto dto) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recurso no encontrado con id: " + id));

        resource.setDescripcion(dto.getDescripcion());

        return resourceRepository.save(resource);
    }

    public void eliminarRecurso(Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new NotFoundException("Recurso no encontrado con id: " + id);
        }
        resourceRepository.deleteById(id);
    }
}
