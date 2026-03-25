package com.machinecoding.flipkartwallet.service;

import com.machinecoding.flipkartwallet.dto.LoadMoneyRequest;
import com.machinecoding.flipkartwallet.dto.RegisterUserRequest;
import com.machinecoding.flipkartwallet.dto.TransferRequest;
import com.machinecoding.flipkartwallet.entity.Role;
import com.machinecoding.flipkartwallet.entity.User;
import com.machinecoding.flipkartwallet.repository.TransactionRepository;
import com.machinecoding.flipkartwallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(RegisterUserRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .balance(0.0)
                .role(request.getRole() != null ? request.getRole() : Role.USER)
                .build();

        return userRepository.save(user);
    }

    public String loadMoney(LoadMoneyRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow();

        user.setBalance(user.getBalance() + request.getAmount());
        userRepository.save(user);

        return "Money loaded";
    }

    public String transfer(TransferRequest request) {

        User sender = userRepository.findById(request.getSenderId()).orElseThrow();
        User receiver = userRepository.findById(request.getReceiverId()).orElseThrow();

        if (sender.getBalance() < request.getAmount()) {
            throw new RuntimeException("Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - request.getAmount());
        receiver.setBalance(receiver.getBalance() + request.getAmount());

        userRepository.save(sender);
        userRepository.save(receiver);

        return "Transfer successful";
    }

    public Double getBalance(Long userId) {
        return userRepository.findById(userId).orElseThrow().getBalance();
    }
}
