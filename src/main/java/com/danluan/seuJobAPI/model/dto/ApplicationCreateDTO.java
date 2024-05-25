package com.danluan.seuJobAPI.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationCreateDTO {
    private Integer jobId;
    private Integer serviceId;
    @NotNull(message = "Worker ID is required")
    private Integer workerId;
}
