package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository; // trazendo métodos de acesso ao banco de dados
import org.springframework.stereotype.Repository;// marcando a interface como componente de acesso

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCpf(String cpf);
}
