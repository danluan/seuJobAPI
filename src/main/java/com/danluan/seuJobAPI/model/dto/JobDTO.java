package com.danluan.seuJobAPI.model.dto;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    Integer companyId;
    String description;
    String contract_type;
    String location;
    String title;
    String salary;
    String publish_date;
}
