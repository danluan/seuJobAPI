package com.danluan.seuJobAPI.exception;

public class WorkerNotFoundException extends RuntimeException {
    public WorkerNotFoundException() { super("Worker ID not found."); }
}
