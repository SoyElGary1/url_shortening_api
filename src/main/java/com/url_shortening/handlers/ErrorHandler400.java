package com.url_shortening.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler400 {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorData>> handleError400(MethodArgumentNotValidException e){
        List<ValidationErrorData> errors = e.getFieldErrors().stream().map(ValidationErrorData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record ValidationErrorData(String field, String messageError){
        public ValidationErrorData (FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
