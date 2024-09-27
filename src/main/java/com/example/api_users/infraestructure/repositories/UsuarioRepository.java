package com.example.api_users.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_users.domain.entities.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    
    Boolean existsByEmail(String email);
}
