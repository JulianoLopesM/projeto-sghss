package com.vidaplus.sghss.service;

import com.vidaplus.sghss.entity.Paciente;
import com.vidaplus.sghss.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Paciente cadastrar(Paciente novoPaciente) {
        // ... (verificação de email/cpf) ...

        // Criptografa a senha
        String senhaCriptografada = passwordEncoder.encode(novoPaciente.getSenhaHash());
        novoPaciente.setSenhaHash(senhaCriptografada);

        return pacienteRepository.save(novoPaciente);
    }

    public Optional<Paciente> buscarPacientePorId(Long id) {
        return pacienteRepository.findById(id);
    }
    public Optional<Paciente> buscarPacientePorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }
    public Paciente atualizarDados(Long id, Paciente dadosAtualizados) {
        return pacienteRepository.findById(id)
                .map(paciente -> {
                    paciente.setNomeCompleto(dadosAtualizados.getNomeCompleto());
                    paciente.setEmail(dadosAtualizados.getEmail());
                    paciente.setTelefone(dadosAtualizados.getTelefone());
                    return pacienteRepository.save(paciente);
                }).orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));
    }
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public void deletarPorId(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new RuntimeException("Erro ao deletar paciente, paciente não encontrado!");
        }
        pacienteRepository.deleteById(id);
    }


}
