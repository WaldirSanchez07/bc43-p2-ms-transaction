package com.nttdata.mstransaction.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassiveProduct {

    private String id;
    private String name;
    private Boolean hasMaintenanceFee;
    private Integer maximumDeposits;
    private Integer maximumWithdrawals;
    private Double transactionFee;
    //Plazo fijo
    private Integer transactionDay;

}
