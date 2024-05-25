package com.danluan.seuJobAPI.exception;

public class WorkerAlreadyAppliedException extends RuntimeException{
    public WorkerAlreadyAppliedException() {
        super("Worker already applied.");
    }
}
