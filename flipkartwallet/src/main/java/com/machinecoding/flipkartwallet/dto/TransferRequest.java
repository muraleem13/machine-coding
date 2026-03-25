package com.machinecoding.flipkartwallet.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private Long senderId;
    private Long receiverId;
    private Double amount;
}
