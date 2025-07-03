package com.vidaplus.sghss.controller;

import com.vidaplus.sghss.entity.Profissional;
import com.vidaplus.sghss.service.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalService profissionalService;

    @PostMapping
    public Profissional cadastrar(@RequestBody Profissional profissional) {
        return profissionalService.cadastrar(profissional);
    }

    @GetMapping
    public List<Profissional> listarTodos() {
        return profissionalService.listarTodos();
    }

    @GetMapping("/especialidade/{especialidade}")
    public List<Profissional> buscarPorEspecialidade(@PathVariable String especialidade) {
        return profissionalService.buscarPorEspecialidade(especialidade);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            profissionalService.deletarPorId(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found
        }
    }

}