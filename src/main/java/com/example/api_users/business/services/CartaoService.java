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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final CartaoConverter cartaoConverter;
    private final UsuarioService usuarioService;

    private CartaoEntity salvarCartao(CartaoEntity cartao) {
        return cartaoRepository.saveAndFlush(cartao);
    }

    public CartaoDTO registrarCartao(CartaoDTO cartao) {
        try {
            UsuarioEntity usuario = usuarioService.buscaUsuario(cartao.getUsuarioId());
            CartaoEntity cartaoEntity = cartaoConverter.paraCartaoEntity(cartao, usuario);
            return cartaoConverter.paraCartaoDTO(salvarCartao(cartaoEntity), usuario);
        } catch (Exception e) {
           throw new BusinessException("Erro ao cadastrar cartão", e);
        }
    }

    public CartaoEntity buscaCartao(String numeroCartao) {
        try {
            CartaoEntity cartao = cartaoRepository.findByNumeroCartao(numeroCartao);
            return cartao;
        } catch (Exception e) {
            throw new BusinessException("Erro ao buscar cartão", e);
        }
    }

    public String validarCartao(CartaoDTO cartao) {
        try {
            CartaoEntity cartaoEntity = buscaCartao(cartao.getNumeroCartao());
            notNull(cartaoEntity, "Cartão não identificado");

            if(cartaoEntity.getAtivo().equals(true) && cartaoEntity.getCvv().equals(cartao.getCvv())) {
                return "Cartão válido";
            }

            return "Cartão inválido";
        } catch (IllegalArgumentException e) {
            throw new UnprocessableEntityException(e.getMessage());
        } catch (Exception e) {
            throw new BusinessException("Erro ao validar cartão", e);
        }
    }

    
}
