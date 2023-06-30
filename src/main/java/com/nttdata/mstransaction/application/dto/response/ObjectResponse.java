package com.nttdata.mstransaction.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectResponse {

    private Integer statusCode;
    private String message;
    private Object data;

}
