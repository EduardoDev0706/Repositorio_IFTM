package br.edu.iftm.tspi.pbackorm.e_commerce.dto.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= CategoriaExistsValidator.class)
public @interface CategoriaExists {

    String message() default "Categoria não encontrada";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
