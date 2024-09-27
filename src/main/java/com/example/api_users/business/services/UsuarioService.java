package com.example.api_users.business.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.api_users.business.converter.UsuarioConverter;
import com.example.api_users.domain.dto.UsuarioDTO;
import com.example.api_users.domain.entities.UsuarioEntity;
import com.example.api_users.infraestructure.exceptions.*;
import com.example.api_users.infraestructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    private UsuarioEntity salvarUsuario(UsuarioEntity usuario) {
        return usuarioRepository.saveAndFlush(usuario);
    }

    public UsuarioDTO registrarUsuario(UsuarioDTO usuario) {
        try {
            Boolean usuarioExiste = usuarioRepository.existsByEmail(usuario.getEmail());
            if(usuarioExiste.equals(true)){
                throw new ConflictException("Já existe usuário cadastrado com o e-mail informado!");
            }

            UsuarioEntity usuarioEntity = usuarioConverter.paraUsuarioEntity(usuario);
            return usuarioConverter.paraUsuarioDTO(salvarUsuario(usuarioEntity));
        } catch(ConflictException e) {
            throw new ConflictException(e.getMessage());
        } catch(Exception e) {
            throw new BusinessException("Erro ao cadastrar usuário", e);
        }
    }

    public List<UsuarioDTO> buscaTodosUsuarios() {
        try {
            List<UsuarioEntity> usuarios = usuarioRepository.findAll();
            return usuarioConverter.paraListaDeUsuariosDTO(usuarios);
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar usuários", e);
        }
    }
    
}
