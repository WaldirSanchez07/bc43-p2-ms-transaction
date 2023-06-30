package com.nttdata.mstransaction.application.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalRequest {

    private String accountId;
    private Double amount;

}
