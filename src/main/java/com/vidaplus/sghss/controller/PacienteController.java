package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.entity.Paciente;
import com.vidaplus.sghss.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Paciente> cadastrar(@RequestBody Paciente paciente) {
        return ResponseEntity.status(201).body(pacienteService.cadastrarPaciente(paciente));
    }

    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id) {
        return pacienteService.buscarPacientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            pacienteService.deletarPorId(id);
            // Retorna status 204 No Content, que é o padrão para exclusão bem-sucedida.
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Se o serviço lançar um erro (paciente não encontrado), retorna 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }
}