package it.shine.gamingverse.annotations.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhotoSizeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxPhotoSizeConstraint {

    String message() default "Photo size must not exceed 2 MB";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

