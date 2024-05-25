package com.danluan.seuJobAPI.exception;

public class UserIdAlreadyInUseException extends RuntimeException {
    public UserIdAlreadyInUseException() { super("User ID already is a Worker."); }

}
