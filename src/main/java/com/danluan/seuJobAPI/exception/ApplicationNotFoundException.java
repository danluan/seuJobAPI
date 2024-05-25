package com.danluan.seuJobAPI.exception;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException() { super("Application not found."); }
}