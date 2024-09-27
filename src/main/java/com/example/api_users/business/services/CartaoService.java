package com.example.api_users.business.services;

import static org.springframework.util.Assert.notNull;
import org.springframework.stereotype.Service;
import com.example.api_users.business.converter.CartaoConverter;
import com.example.api_users.domain.dto.CartaoDTO;
import com.example.api_users.domain.entities.CartaoEntity;
import com.example.api_users.domain.entities.UsuarioEntity;
import com.example.api_users.infraestructure.exceptions.BusinessException;
import com.example.api_users.infraestructure.exceptions.UnprocessableEntityException;
import com.example.api_users.infraestructure.repositories.CartaoRepository;
import com.example.api_users.infraestructure.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CartaoConverter cartaoConverter;

    private CartaoEntity salvarCartao(CartaoEntity cartao) {
        return cartaoRepository.saveAndFlush(cartao);
    }

    public CartaoDTO registrarCartao(CartaoDTO cartao) {
        try {
            UsuarioEntity usuarioEntity = usuarioRepository.getReferenceById(cartao.getUsuarioId());
            CartaoEntity cartaoEntity = cartaoConverter.paraCartaoEntity(cartao, usuarioEntity);

            return cartaoConverter.paraCartaoDTO(salvarCartao(cartaoEntity), usuarioEntity);
        } catch (Exception e) {
           throw new BusinessException("Erro ao cadastrar cartão", e);
        }
    }

    public String validarCartao(CartaoDTO cartao) {
        try {
            CartaoEntity cartaoEntity = cartaoRepository.findByNumeroCartao(cartao.getNumeroCartao());
            notNull(cartaoEntity, "Cartão não identificado");

            if(cartaoEntity.getAtivo().equals(true) && cartaoEntity.getCvv().equals(cartao.getCvv())) {
                return "Cartão válido";
            }

            return "Cartão inválido";
        } catch (IllegalArgumentException e) {
            throw new UnprocessableEntityException(e.getMessage());
        }catch (Exception e) {
            throw new BusinessException("Erro ao validar cartão", e);
        }
    }
    
    
}
