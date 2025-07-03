package com.vidaplus.sghss.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "profissionais")
@Setter
@Getter
public class Profissional {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String nomeCompleto;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 255)
    private String senhaHash;
    @Column(length = 100)
    private String especialidade;
    @Column(length = 20)
    private String conselhoProfissional;
    @Column(length = 20)
    private String numeroConselho;




}
