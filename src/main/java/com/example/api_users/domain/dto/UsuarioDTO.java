package com.example.api_users.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    String nome;

    String email;

    String cpf;

    EnderecoDTO endereco;
    
}
