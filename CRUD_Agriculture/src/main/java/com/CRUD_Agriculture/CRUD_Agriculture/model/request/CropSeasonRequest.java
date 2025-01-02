package com.CRUD_Agriculture.CRUD_Agriculture.model.request;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.enums.SeasonStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
public class CropSeasonRequest {
    @NotBlank(message = "Tên mùa vụ không được để trống")
    private String seasonName;

    @NotBlank(message = "Loại cây trồng không được để trống")
    private String cropType;

    @NotNull(message = "Diện tích không được để trống")
    @Positive(message = "Diện tích phải lớn hơn 0")
    private Double area;

    @NotNull(message = "Ngày gieo trồng không được để trống")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate plantingDate;

    @NotNull(message = "Ngày dự kiến thu hoạch không được để trống")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedHarvestDate;

    @NotNull(message = "Trạng thái không được để trống")
    private SeasonStatus status;

    public CropSeasonRequest() {}

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }

    public LocalDate getExpectedHarvestDate() {
        return expectedHarvestDate;
    }

    public void setExpectedHarvestDate(LocalDate expectedHarvestDate) {
        this.expectedHarvestDate = expectedHarvestDate;
    }

    public SeasonStatus getStatus() {
        return status;
    }

    public void setStatus(SeasonStatus status) {
        this.status = status;
    }
}

