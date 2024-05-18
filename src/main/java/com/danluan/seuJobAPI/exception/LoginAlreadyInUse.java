package com.danluan.seuJobAPI.exception;

public class LoginAlreadyInUse extends RuntimeException {
    public LoginAlreadyInUse() { super("Login already in use."); }
}


