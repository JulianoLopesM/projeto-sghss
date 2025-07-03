package com.vidaplus.sghss.repository;

import com.vidaplus.sghss.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByPacienteIdAndDataHoraAfter(Long pacienteId, LocalDateTime dataHora);
    List<Consulta> findByPacienteIdAndDataHoraBefore(Long pacienteId, LocalDateTime dataHora);
    List<Consulta> findByProfissionalIdAndDataHoraBetween(Long profissionalId, LocalDateTime inicio, LocalDateTime fim);
}