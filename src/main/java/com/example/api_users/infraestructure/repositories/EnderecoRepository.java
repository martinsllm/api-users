package com.example.api_users.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_users.domain.entities.EnderecoEntity;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
    
}
