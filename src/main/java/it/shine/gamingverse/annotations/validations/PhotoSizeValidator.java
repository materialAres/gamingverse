package it.shine.gamingverse.annotations.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhotoSizeValidator implements ConstraintValidator<MaxPhotoSizeConstraint, byte[]> {

    private static final long MAX_SIZE_BYTES = 2 * 1024 * 1024;

    @Override
    public boolean isValid(byte[] photo, ConstraintValidatorContext context) {
        if (photo == null) {
            return true;
        }

        return photo.length <= MAX_SIZE_BYTES;
    }

}
