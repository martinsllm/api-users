package com.example.api_users.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartaoDTO {

    private String numeroCartao;

    private String cvv;

    private String mesVencimento;

    private String anoVencimento;

    private Long usuarioId;
    
}
