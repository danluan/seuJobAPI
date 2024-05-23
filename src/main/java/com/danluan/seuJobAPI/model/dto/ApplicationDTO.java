package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.enums.ApplicationStatus;
import com.danluan.seuJobAPI.model.Application;
import com.danluan.seuJobAPI.model.Company;
import com.danluan.seuJobAPI.model.Freelancer;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    Integer id;
    ApplicationStatus status;
    Integer jobId;
    Integer serviceId;
    Integer workerId;
    Date dateApply;
}
