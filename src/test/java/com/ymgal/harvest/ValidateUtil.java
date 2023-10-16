package com.ymgal.harvest;


import com.sun.istack.internal.Nullable;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Objects;
import java.util.Set;

public final class ValidateUtil {

    private static volatile Validator validator = Validation.byDefaultProvider()
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory()
            .getValidator();

    private ValidateUtil() {
    }

    public static void validate(Object object) {
        Set<ConstraintViolation<Object>> result = validator.validate(object);
        if (result == null || result.isEmpty()) return;

        //只拿第一个信息
        ConstraintViolation<Object> next = result.iterator().next();
        String message = next.getMessage();

        throw new IllegalArgumentException(message);
    }
}
