package com.backend.appmovil1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.appmovil1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByCorreo(String correo);
}