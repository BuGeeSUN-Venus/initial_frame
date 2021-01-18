package com.example.initial_frame.common.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ValidationException;
import java.util.List;

public class ResultResponseUtil {
    public static void check(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            String erros = "";
            for (FieldError fieldError : fieldErrors) {
                String field = fieldError.getField();
                String defaultMessage = fieldError.getDefaultMessage();
                erros += (field + ":" + defaultMessage);
            }
            throw new ValidationException(erros);
        }
    }
}
