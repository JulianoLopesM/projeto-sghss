package com.vidaplus.sghss.service;

import com.vidaplus.sghss.entity.Profissional;
import com.vidaplus.sghss.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public Profissional cadastrar(Profissional novoProfissional) {
        // Lógica de validação...
        return profissionalRepository.save(novoProfissional);
    }

    public Optional<Profissional> buscarPorId(Long id) {
        return profissionalRepository.findById(id);
    }

    public Profissional atualizarDados(Long id, Profissional dadosAtualizados) {
        return profissionalRepository.findById(id)
                .map(profissional -> {
                    profissional.setNomeCompleto(dadosAtualizados.getNomeCompleto());
                    profissional.setEspecialidade(dadosAtualizados.getEspecialidade());
                    return profissionalRepository.save(profissional);
                }).orElseThrow(() -> new RuntimeException("Profissional não encontrado!"));
    }

    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    public List<Profissional> buscarPorEspecialidade(String especialidade) {
        return profissionalRepository.findByEspecialidade(especialidade);
    }
    public void deletarPorId(Long id) {
        // Verifica se o profissional existe antes de tentar deletar
        if (!profissionalRepository.existsById(id)) {
            throw new RuntimeException("Erro ao deletar: Profissional não encontrado com o ID " + id);
        }
        profissionalRepository.deleteById(id);
    }
}