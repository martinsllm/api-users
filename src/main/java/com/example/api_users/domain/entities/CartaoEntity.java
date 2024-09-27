package com.example.api_users.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "CartaoEntity")
@Table(name = "cartao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cartao", nullable = false)
    private String numeroCartao;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "mes_vencimento", nullable = false)
    private String mesVencimento;

    @Column(name = "ano_vencimento", nullable = false)
    private String anoVencimento;

    @Column(name = "ativo")
    private Boolean ativo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioEntity usuario;
    
}
