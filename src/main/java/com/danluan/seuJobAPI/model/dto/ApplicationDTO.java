package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.enums.ApplicationStatus;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    int id;
    ApplicationStatus status;
    JobDTO job;
    ServiceDTO service;
    WorkerDTO worker;
    Date dateApply;
}
