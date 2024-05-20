package com.danluan.seuJobAPI.exception;

public class WorkerIdAlreadyInUse extends RuntimeException {
    public WorkerIdAlreadyInUse() {
        super("Worker already has a resume");
    }
}
