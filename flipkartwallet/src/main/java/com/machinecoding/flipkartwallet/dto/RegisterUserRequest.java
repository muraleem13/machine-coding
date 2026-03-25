package com.machinecoding.flipkartwallet.dto;

import com.machinecoding.flipkartwallet.entity.Role;
import lombok.Data;

@Data
public class RegisterUserRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}