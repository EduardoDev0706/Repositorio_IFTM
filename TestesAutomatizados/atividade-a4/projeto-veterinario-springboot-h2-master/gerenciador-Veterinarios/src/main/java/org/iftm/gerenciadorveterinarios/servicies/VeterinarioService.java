package org.iftm.gerenciadorveterinarios.servicies;

import java.util.List;
import java.util.Optional;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeterinarioService {
    @Autowired
    private VeterinarioRepository repositorio;

    @Transactional(readOnly = true)
    public List<Veterinario> buscaVeterinariosComParteNome(String nome){
        return repositorio.findByNomeContains(nome);
    }

    @Transactional(readOnly = true)
    public Optional<Veterinario> buscaVeterinariosPeloId(Integer id){
        return repositorio.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Veterinario> buscaTodosVeterinarios(){
        return repositorio.findAll();
    }

    @Transactional
    public Veterinario salvar(Veterinario veterinario){
        return repositorio.save(veterinario);
    }

    @Transactional
    public void apagar(Veterinario veterinario){
        repositorio.delete(veterinario);
    }
}
