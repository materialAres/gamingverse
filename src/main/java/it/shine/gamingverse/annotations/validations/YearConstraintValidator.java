package it.shine.gamingverse.annotations.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearConstraintValidator implements ConstraintValidator<YearConstraint, Year> {

    @Override
    public boolean isValid(Year year, ConstraintValidatorContext context) {
        if (year == null) {
            return true;
        }

        Year currentYear = Year.now();

        return year.isAfter(Year.of(1949)) && (year.isBefore(currentYear) || year.equals(currentYear));
    }

}

