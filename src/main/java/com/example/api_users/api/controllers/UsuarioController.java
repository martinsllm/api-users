package com.example.api_users.api.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.api_users.business.services.UsuarioService;
import com.example.api_users.domain.dto.UsuarioDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<UsuarioDTO> register(@RequestBody @Valid UsuarioDTO usuario) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(usuario));
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> listaTodosUsuarios() {
        return ResponseEntity.ok(usuarioService.buscaTodosUsuarios());
    }
    
}
