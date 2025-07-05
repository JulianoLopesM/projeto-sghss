package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.entity.Consulta;
import com.vidaplus.sghss.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    // Classe auxiliar para receber os dados do agendamento
    public static class AgendamentoRequest {
        public Long pacienteId;
        public Long profissionalId;
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        public LocalDateTime dataHora;
    }

    @PostMapping("/agendar")
    public Consulta agendar(@RequestBody AgendamentoRequest request) {
        return consultaService.agendarConsulta(request.pacienteId, request.profissionalId, request.dataHora);
    }

    @PatchMapping("/{consultaId}/cancelar")
    public ResponseEntity<String> cancelar(@PathVariable Long consultaId) {
        try {
            consultaService.cancelarConsulta(consultaId);
            return ResponseEntity.ok("Consulta cancelada com sucesso");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    static class RemarcarRequest {
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        public LocalDateTime novaDataHora;
    }

    @PatchMapping("/{consultaId}/remarcar")
    public ResponseEntity<Consulta> remarcar(@PathVariable Long consultaId, @RequestBody RemarcarRequest request) {
        try {
            Consulta consultaRemarcada = consultaService.remarcarConsulta(consultaId, request.novaDataHora);
            return ResponseEntity.ok(consultaRemarcada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paciente/{pacienteId}/futuras")
    public List<Consulta> listarFuturasPorPaciente(@PathVariable Long pacienteId) {
        return consultaService.listarConsultasFuturasPorPaciente(pacienteId);
    }

    @GetMapping("/paciente/{pacienteId}/historico")
    public List<Consulta> listarHistoricoPorPaciente(@PathVariable Long pacienteId) {
        return consultaService.listarHistoricoConsultasPorPaciente(pacienteId);
    }
}