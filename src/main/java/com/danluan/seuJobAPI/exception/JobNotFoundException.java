package com.danluan.seuJobAPI.exception;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() { super("Job not found."); }
}


