package com.nttdata.mstransaction.infrastructure.dao.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "transactions")
public class TransactionEntity {

    @Id
    private String id;
    private String type; //Deposito, Retiro, Consumo, Comision, Transferencia
    private String description;
    private ObjectId accountId;
    private ObjectId anotherAccountId;
    private Double amount;
    private LocalDateTime date;

}
