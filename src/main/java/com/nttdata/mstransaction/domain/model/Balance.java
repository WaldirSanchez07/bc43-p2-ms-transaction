package com.nttdata.mstransaction.domain.model;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {

    private String id;
    private ObjectId accountId;
    private Double amount;
    private LocalDateTime date;

}
