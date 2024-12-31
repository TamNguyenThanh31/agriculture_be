package com.CRUD_Agriculture.CRUD_Agriculture.model.response;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.enums.SeasonStatus;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CropSeasonResponse {
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
