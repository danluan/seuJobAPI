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
public class ServiceDTO {
    private Integer id;
    private String title;
    private String description;
    private String location;
    private Float remuneration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date publishDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date endDate;

    public ServiceDTO(Service service) {
        this.id = service.getId();
        this.title = service.getTitle();
        this.description = service.getDescription();
        this.location = service.getLocation();
        this.remuneration = service.getRemuneration();
        this.publishDate = service.getPublishDate();
        this.endDate = service.getEndDate();
    }
}
