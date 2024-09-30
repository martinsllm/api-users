package com.example.api_users.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_users.domain.entities.CartaoEntity;


public interface CartaoRepository extends JpaRepository<CartaoEntity, Long> {

    Boolean existsByUsuarioId(Long usuarioId);
    
    CartaoEntity findByNumeroCartao(String numeroCartao);
}
