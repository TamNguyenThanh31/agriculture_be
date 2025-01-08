package com.CRUD_Agriculture.CRUD_Agriculture.controllers;


import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropSeasonRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropSeasonResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.services.CropSeasonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seasons")
//@RequiredArgsConstructor
@Slf4j
public class CropSeasonController {

    private static final Logger logger = LoggerFactory.getLogger(CropSeasonController.class);
    private final CropSeasonService cropSeasonService;

    // Constructor thủ công
    public CropSeasonController(CropSeasonService cropSeasonService) {
        this.cropSeasonService = cropSeasonService;
    }

    @GetMapping
    public ResponseEntity<List<CropSeasonResponse>> getAllSeasons() {
        List<CropSeasonResponse> seasons = cropSeasonService.getAllSeasons();
        return ResponseEntity.ok(seasons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropSeasonResponse> getSeasonById(@PathVariable Long id) {
        CropSeasonResponse season = cropSeasonService.getSeasonById(id);
        return ResponseEntity.ok(season);
    }

    @PostMapping("/add-season")
    public ResponseEntity<CropSeasonResponse> createSeason(@Validated @RequestBody CropSeasonRequest request) {
        // Log raw request body
        logger.info("Raw request received: {}", request);

        // Log từng field
        logger.info("Controller received request with:");
//        logger.info("seasonName: {}", request.getSeasonName());
//        logger.info("cropType: {}", request.getCropType());
        CropSeasonResponse createdSeason = cropSeasonService.createSeason(request);
        return new ResponseEntity<>(createdSeason, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CropSeasonResponse> updateSeason(
            @PathVariable Long id,
            @Valid @RequestBody CropSeasonRequest request) {
        CropSeasonResponse updatedSeason = cropSeasonService.updateSeason(id, request);
        return ResponseEntity.ok(updatedSeason);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long id) {
        cropSeasonService.deleteSeason(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-costs")
    public ResponseEntity<List<Map<String, Object>>> getTotalCostsBySeasons() {
        List<Map<String, Object>> totalCosts = cropSeasonService.getTotalCostsBySeasons();
        return ResponseEntity.ok(totalCosts);
    }

}
