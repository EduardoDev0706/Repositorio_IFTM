package org.iftm.gerenciadorveterinarios.services;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository repository;

    public Funcionario cadastrar(Funcionario funcionario) {
        if (funcionario.getSalario() < 1412.00) { // Validação do Salário Mínimo
            throw new IllegalArgumentException("O salário não pode ser inferior ao mínimo vigente");
        }
        funcionario.setEmFerias(false);
        return repository.save(funcionario);
    }

    public Funcionario concederFerias(Integer id) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        funcionario.setEmFerias(true);
        return repository.save(funcionario);
    }
}