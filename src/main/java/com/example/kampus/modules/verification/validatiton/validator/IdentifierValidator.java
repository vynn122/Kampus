package com.example.kampus.modules.verification.validatiton.validator;


import com.example.kampus.modules.verification.validatiton.annotation.ValidIdentifier;
import com.example.kampus.modules.verification.validatiton.util.EmailValidatorUtil;
import com.example.kampus.modules.verification.validatiton.util.PhoneValidatorUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdentifierValidator implements ConstraintValidator<ValidIdentifier, String> {
    private final EmailValidatorUtil emailValidator;
    private final PhoneValidatorUtil phoneValidatorUtil;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()){
            return false;
        }
        return emailValidator.isValid(value) || phoneValidatorUtil.isValid(value);
    }
}
