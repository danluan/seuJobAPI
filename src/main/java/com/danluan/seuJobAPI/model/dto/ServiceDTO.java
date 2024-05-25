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
public class ServiceDTO {
    private Integer id;

    @NotNull(message = "Freelancer Id is required")
    private Integer freelancerId;

    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Description is required")
    private String description;

    @NotEmpty(message = "Location is required")
    private String location;

    @NotNull(message = "Remuneration is required")
    private Float remuneration;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date publishDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm:ss")
    private Date endDate;

    public ServiceDTO(Service service) {
        this.freelancerId = service.getFreelancer().getId();
        this.id = service.getId();
        this.title = service.getTitle();
        this.description = service.getDescription();
        this.location = service.getLocation();
        this.remuneration = service.getRemuneration();
        this.publishDate = service.getPublishDate();
        this.endDate = service.getEndDate();
    }
}
