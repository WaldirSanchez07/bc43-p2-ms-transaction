package com.nttdata.mstransaction.infrastructure.dao.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accounts")
public class AccountEntity {

    @Id
    private String id;
    private String accountNumber;
    private ObjectId clientId;
    private String passiveProductId;
    private String activeProductId;

}
