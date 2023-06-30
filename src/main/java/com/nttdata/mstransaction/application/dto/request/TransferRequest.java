package com.nttdata.mstransaction.application.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

  private String accountId;
  private String anotherAccountId;
  private Double amount;

}
