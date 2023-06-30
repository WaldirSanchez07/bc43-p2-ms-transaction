package com.nttdata.mstransaction.infrastructure.handler;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseBody
    public Single<ErrorResponse> handleValidationException(WebExchangeBindException ex) {
        Integer statusCode = HttpStatus.UNPROCESSABLE_ENTITY.value();
        return Flowable.fromIterable(ex.getFieldErrors())
                .toMap(FieldError::getField, FieldError::getDefaultMessage, HashMap::new)
                .map(errors -> new ErrorResponse("Error de validación", statusCode, errors));
    }

}
