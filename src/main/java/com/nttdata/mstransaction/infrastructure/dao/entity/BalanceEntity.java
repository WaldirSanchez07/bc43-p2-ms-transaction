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
@Document(collection = "balances")
public class BalanceEntity {

    @Id
    private String id;
    private ObjectId accountId;
    private Double amount;
    private LocalDateTime date;

}
