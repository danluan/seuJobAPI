package com.danluan.seuJobAPI.model.dto;

import com.danluan.seuJobAPI.model.Service;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceUpdateDTO {
    private String title;
    private String description;
    private String location;
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
