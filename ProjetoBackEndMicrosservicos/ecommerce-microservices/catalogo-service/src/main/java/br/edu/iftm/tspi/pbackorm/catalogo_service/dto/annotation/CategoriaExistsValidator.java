package br.edu.iftm.tspi.pbackorm.catalogo_service.dto.annotation;

import org.springframework.stereotype.Component;

import br.edu.iftm.tspi.pbackorm.catalogo_service.repository.CategoriaRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CategoriaExistsValidator implements ConstraintValidator<CategoriaExists,Integer>{

    private final CategoriaRepository repository;

    @Override
    public boolean isValid(Integer idCategoria,ConstraintValidatorContext context) {
        return repository.existsById(idCategoria);
    }

}
