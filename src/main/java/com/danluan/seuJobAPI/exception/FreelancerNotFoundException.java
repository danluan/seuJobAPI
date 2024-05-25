package com.danluan.seuJobAPI.exception;

public class FreelancerNotFoundException extends RuntimeException {
    public FreelancerNotFoundException() {
        super("Freelancer not found.");
    }
}
