package com.example.baitap10.validator;

import com.example.baitap10.dto.RegisterRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterRequest> {
    
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
    }
    
    @Override
    public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext context) {
        if (registerRequest.getPassword() == null || registerRequest.getConfirmPassword() == null) {
            return true;
        }
        return registerRequest.getPassword().equals(registerRequest.getConfirmPassword());
    }
}
