package com.example.kampus.modules.verification.validatiton.annotation;


import com.example.kampus.modules.verification.validatiton.validator.IdentifierValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdentifierValidator.class)
public @interface ValidIdentifier {
    String message()default  "Invalid Email or Phone Number";
    Class<?> [] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
