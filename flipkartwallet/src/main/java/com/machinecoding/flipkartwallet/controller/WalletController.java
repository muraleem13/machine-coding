package com.machinecoding.flipkartwallet.controller;

import com.machinecoding.flipkartwallet.dto.LoadMoneyRequest;
import com.machinecoding.flipkartwallet.dto.TransferRequest;
import com.machinecoding.flipkartwallet.service.WalletService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/load")
    public String loadMoney(@RequestBody LoadMoneyRequest request) {
        return walletService.loadMoney(request);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody TransferRequest request) {
        return walletService.transfer(request);
    }

    @GetMapping("/balance/{userId}")
    public Double getBalance(@PathVariable Long userId) {
        return walletService.getBalance(userId);
    }
}
