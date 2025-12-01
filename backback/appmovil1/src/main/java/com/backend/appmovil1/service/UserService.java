package com.backend.appmovil1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.appmovil1.dto.UserDto;
import com.backend.appmovil1.exception.EmailAlreadyExistsException;
import com.backend.appmovil1.exception.NotFoundException;
import com.backend.appmovil1.model.User;
import com.backend.appmovil1.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User crearUsuario(UserDto dto) {

        if (userRepository.existsByCorreo(dto.getCorreo())) {
            throw new EmailAlreadyExistsException("El correo electrónico ya está registrado: " + dto.getCorreo());
        }

        User user = User.builder()
                .nombre(dto.getNombre())
                .correo(dto.getCorreo())
                .clave(dto.getClave()) // Idealmente, la contraseña debería ser hasheada
                .aceptaTerminos(dto.getAceptaTerminos())
                .build();

        return userRepository.save(user);
    }

    public List<User> obtenerTodos() {
        return userRepository.findAll();
    }

    public User actualizarUsuario(Long id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado con id: " + id));

        user.setNombre(dto.getNombre());
        user.setCorreo(dto.getCorreo());
        user.setClave(dto.getClave());
        user.setAceptaTerminos(dto.getAceptaTerminos());

        return userRepository.save(user);
    }

    public void eliminarUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }
}