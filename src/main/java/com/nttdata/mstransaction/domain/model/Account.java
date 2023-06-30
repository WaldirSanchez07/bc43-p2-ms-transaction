package com.nttdata.mstransaction.domain.model;

import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String id;
    private String accountNumber;
    private ObjectId clientId;
    private String passiveProductId;
    private String activeProductId;

}
