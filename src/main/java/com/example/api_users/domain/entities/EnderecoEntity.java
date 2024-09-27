package com.example.api_users.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "EnderecoEntity")
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rua;

    private Long numero;

    private String bairro;

    private String cep;

    private String cidade;

}
