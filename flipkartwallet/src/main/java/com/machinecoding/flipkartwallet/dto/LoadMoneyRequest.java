package com.machinecoding.flipkartwallet.dto;

import lombok.Data;

@Data
public class LoadMoneyRequest {
    private Long userId;
    private Double amount;
}
