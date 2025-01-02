package com.CRUD_Agriculture.CRUD_Agriculture.model.dto;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.enums.SeasonStatus;
import lombok.*;

import java.time.LocalDate;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CropSeasonDTO {
    private Long id;
    private String seasonName;
    private String cropType;
    private Double area;
    private LocalDate plantingDate;
    private LocalDate expectedHarvestDate;
    private SeasonStatus status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
