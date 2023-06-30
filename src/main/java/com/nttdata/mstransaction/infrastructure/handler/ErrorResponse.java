package com.nttdata.mstransaction.infrastructure.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
class ErrorResponse {

    private String message;
    private Integer status;
    private Map<String, String> errors;
}
