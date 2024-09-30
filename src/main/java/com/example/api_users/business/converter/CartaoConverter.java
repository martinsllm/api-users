package com.example.api_users.business.converter;

import org.springframework.stereotype.Component;
import com.example.api_users.domain.dto.CartaoDTO;
import com.example.api_users.domain.entities.CartaoEntity;
import com.example.api_users.domain.entities.UsuarioEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CartaoConverter {

    public CartaoEntity paraCartaoEntity(CartaoDTO cartao, UsuarioEntity usuario) {
        return CartaoEntity.builder()
            .numeroCartao(cartao.getNumeroCartao())
            .cvv(cartao.getCvv())
            .mesVencimento(cartao.getMesVencimento())
            .anoVencimento(cartao.getAnoVencimento())
            .ativo(true)
            .usuario(usuario)
            .build();
    }
    
    public CartaoDTO paraCartaoDTO(CartaoEntity cartao, UsuarioEntity usuario) {
        return CartaoDTO.builder()
            .numeroCartao(cartao.getNumeroCartao())
            .cvv(cartao.getCvv())
            .mesVencimento(cartao.getMesVencimento())
            .anoVencimento(cartao.getAnoVencimento())
            .usuarioId(usuario.getId())
            .build();
    }
}
