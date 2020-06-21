package co.lilpilot.postcard.authcontext.gateway.controller;

import lombok.Data;

@Data
public class LoginRequest {
    private String account;
    private String password;
}
