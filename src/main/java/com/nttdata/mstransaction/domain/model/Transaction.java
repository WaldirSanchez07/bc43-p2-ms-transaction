package com.nttdata.mstransaction.domain.model;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String id;
    private String type; //Deposito, Retiro, Consumo, Comision, Transferencia
    private String description;
    private ObjectId accountId;
    private ObjectId anotherAccountId;
    private Double amount;
    private LocalDateTime date;

}
