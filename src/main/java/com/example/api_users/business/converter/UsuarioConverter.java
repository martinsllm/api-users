package com.example.api_users.business.converter;

import java.util.List;
import org.springframework.stereotype.Component;
import com.example.api_users.domain.dto.EnderecoDTO;
import com.example.api_users.domain.dto.UsuarioDTO;
import com.example.api_users.domain.entities.EnderecoEntity;
import com.example.api_users.domain.entities.UsuarioEntity;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class UsuarioConverter {

    public UsuarioEntity paraUsuarioEntity(UsuarioDTO usuario) {
        return UsuarioEntity.builder()
            .nome(usuario.getNome())
            .email(usuario.getEmail())
            .cpf(usuario.getCpf())
            .dataCadastro(LocalDateTime.now())
            .endereco(paraEnderecoEntity(usuario.getEndereco()))
            .build();
    }

    private EnderecoEntity paraEnderecoEntity(EnderecoDTO endereco) {
        return EnderecoEntity.builder()
            .rua(endereco.getRua())
            .numero(endereco.getNumero())
            .bairro(endereco.getBairro())
            .cep(endereco.getCep())
            .cidade(endereco.getCidade())
            .build();
    }

    public UsuarioDTO paraUsuarioDTO(UsuarioEntity usuario) {
        return UsuarioDTO.builder()
            .nome(usuario.getNome())
            .email(usuario.getEmail())
            .cpf(usuario.getCpf())
            .endereco(paraEnderecoDTO(usuario.getEndereco()))
            .build();
    }

    private EnderecoDTO paraEnderecoDTO(EnderecoEntity endereco) {
        return EnderecoDTO.builder()
            .rua(endereco.getRua())
            .numero(endereco.getNumero())
            .bairro(endereco.getBairro())
            .cep(endereco.getCep())
            .cidade(endereco.getCidade())
            .build();
    }

    public List<UsuarioDTO> paraListaDeUsuariosDTO(List<UsuarioEntity> usuarios) {
        return usuarios.stream().map(this::paraUsuarioDTO).toList();
    }
    
}
