package com.example.api_users.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.api_users.business.services.CartaoService;
import com.example.api_users.domain.dto.CartaoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cartao")
@RequiredArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;

    @PostMapping()
    public ResponseEntity<CartaoDTO> register(@RequestBody @Valid CartaoDTO cartao) {
        return ResponseEntity.ok(cartaoService.registrarCartao(cartao));
    }

    @PostMapping("/validar")
    public ResponseEntity<String> validarCartao(@RequestBody @Valid CartaoDTO cartao) {
        return ResponseEntity.ok(cartaoService.validarCartao(cartao.getNumeroCartao()));
    }
    
}
