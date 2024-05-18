package com.danluan.seuJobAPI.exception;

public class UserIdAlreadyInUse extends RuntimeException {
    public UserIdAlreadyInUse() { super("User ID already is a Worker."); }

}
