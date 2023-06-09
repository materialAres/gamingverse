package it.shine.gamingverse.annotations.validations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = YearConstraintValidator.class)
public @interface YearConstraint {

    String message() default "The year must be between 1950 and the present year";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

