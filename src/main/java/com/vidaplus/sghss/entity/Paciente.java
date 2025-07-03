package com.vidaplus.sghss.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
@Entity
@Table(name = "pacientes")
@Setter
@Getter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String nomeCompleto;

    @Column(unique = true, length = 11)
    private String cpf;

    private LocalDate dataNascimento;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;
    @Column(length = 255)
    private String senhaHash;

}
