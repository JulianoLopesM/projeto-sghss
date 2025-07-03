package com.vidaplus.sghss.service;

import com.vidaplus.sghss.entity.Consulta;
import com.vidaplus.sghss.entity.Paciente;
import com.vidaplus.sghss.entity.Profissional;
import com.vidaplus.sghss.repository.ConsultaRepository;
import com.vidaplus.sghss.repository.PacienteRepository;
import com.vidaplus.sghss.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Consulta agendarConsulta(Long pacienteId, Long profissionalId, LocalDateTime dataHora) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
        Profissional profissional = profissionalRepository.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado!"));

        Consulta novaConsulta = new Consulta();
        novaConsulta.setPaciente(paciente);
        novaConsulta.setProfissional(profissional);
        novaConsulta.setDataHora(dataHora);
        novaConsulta.setStatus("AGENDADA");

        return consultaRepository.save(novaConsulta);
    }

    public void cancelarConsulta(Long consultaId) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));
        consulta.setStatus("CANCELADA");
        consultaRepository.save(consulta);
    }

    public Consulta adicionarObservacao(Long consultaId, String observacao) {
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada!"));
        consulta.setObservacoesProntuario(observacao);
        consulta.setStatus("CONCLUIDA");
        return consultaRepository.save(consulta);
    }

    public List<Consulta> buscarAgendaDoDia(Long profissionalId, LocalDate data) {
        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.atTime(23, 59, 59);
        return consultaRepository.findByProfissionalIdAndDataHoraBetween(profissionalId, inicioDoDia, fimDoDia);
    }

    public List<Consulta> listarConsultasFuturasPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteIdAndDataHoraAfter(pacienteId, LocalDateTime.now());
    }

    public List<Consulta> listarHistoricoConsultasPorPaciente(Long pacienteId) {
        return consultaRepository.findByPacienteIdAndDataHoraBefore(pacienteId, LocalDateTime.now());
    }
}