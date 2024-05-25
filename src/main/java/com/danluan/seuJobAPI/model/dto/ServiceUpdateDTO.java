package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.model.Service;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceUpdateDTO {

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotEmpty(message = "Location is required")
    private String location;

    @NotNull(message = "Remuneration is required")
    private Float remuneration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date endDate;

    public ServiceUpdateDTO(Service service) {
        this.title = service.getTitle();
        this.description = service.getDescription();
        this.location = service.getLocation();
        this.remuneration = service.getRemuneration();
        this.endDate = service.getEndDate();
    }
}
