package com.danluan.seuJobAPI.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() { super("User ID not found."); }

}