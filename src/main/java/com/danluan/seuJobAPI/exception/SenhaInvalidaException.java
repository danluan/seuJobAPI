package com.danluan.seuJobAPI.exception;

import org.springframework.security.core.AuthenticationException;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() { super("Senha inválida."); }

}
