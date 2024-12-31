package com.CRUD_Agriculture.CRUD_Agriculture.entity;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.enums.SeasonStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "crop_seasons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CropSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "season_name", nullable = false)
    private String seasonName;        // Tên mùa vụ

    @Column(name = "crop_type", nullable = false)
    private String cropType;          // Loại cây trồng

    @Column(nullable = false)
    private Double area;              // Diện tích trồng (m2)

    @Column(name = "planting_date", nullable = false)
    private LocalDate plantingDate;   // Ngày gieo trồng

    @Column(name = "expected_harvest_date", nullable = false)
    private LocalDate expectedHarvestDate;  // Ngày dự kiến thu hoạch

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeasonStatus status;      // Trạng thái mùa vụ

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }
}
