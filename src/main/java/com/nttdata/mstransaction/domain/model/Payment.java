package com.nttdata.mstransaction.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private String id;
    private ObjectId accountId;
    private Double amount;
    private Integer state;
    private LocalDate payAt;
    private LocalDateTime paidAt;

}
