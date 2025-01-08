package com.CRUD_Agriculture.CRUD_Agriculture.services.impl;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropSeason;
import com.CRUD_Agriculture.CRUD_Agriculture.mapper.CropSeasonMapper;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropSeasonRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropSeasonResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.repository.CropSeasonRepository;
import com.CRUD_Agriculture.CRUD_Agriculture.repository.CropTaskRepository;
import com.CRUD_Agriculture.CRUD_Agriculture.services.CropSeasonService;
import com.CRUD_Agriculture.CRUD_Agriculture.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
//@RequiredArgsConstructor
public class CropSeasonServiceImpl implements CropSeasonService {

    private final CropSeasonRepository cropSeasonRepository;
    private final CropSeasonMapper cropSeasonMapper;
    private final CropTaskRepository cropTaskRepository;
    private static final Logger logger = LoggerFactory.getLogger(CropSeasonServiceImpl.class);

    // Constructor thủ công
    public CropSeasonServiceImpl(CropSeasonRepository cropSeasonRepository, CropSeasonMapper cropSeasonMapper, CropTaskRepository cropTaskRepository) {
        this.cropSeasonRepository = cropSeasonRepository;
        this.cropSeasonMapper = cropSeasonMapper;
        this.cropTaskRepository = cropTaskRepository;
    }

    @Override
    public List<CropSeasonResponse> getAllSeasons() {
        List<CropSeason> seasons = cropSeasonRepository.findAll();
        return seasons.stream()
                .map(cropSeasonMapper::toResponse)
                .toList();
    }

    @Override
    public CropSeasonResponse getSeasonById(Long id) {
        CropSeason season = findSeasonById(id);
        return cropSeasonMapper.toResponse(season);
    }

    @Override
    @Transactional
    public CropSeasonResponse createSeason(CropSeasonRequest request) {
        // Log chi tiết từng field của request
//        logger.info("Request details:");
//        logger.info("seasonName: {}", request.getSeasonName());
//        logger.info("cropType: {}", request.getCropType());
//        logger.info("area: {}", request.getArea());
//        logger.info("plantingDate: {}", request.getPlantingDate());
//        logger.info("expectedHarvestDate: {}", request.getExpectedHarvestDate());
//        logger.info("status: {}", request.getStatus());
//
//        // Log trước khi mapping
//        logger.info("Starting mapping to entity...");
        CropSeason season = cropSeasonMapper.toEntity(request);

        // Log chi tiết entity sau khi mapping
//        logger.info("Entity after mapping:");
//        logger.info("seasonName: {}", season.getSeasonName());
//        logger.info("cropType: {}", season.getCropType());
//        logger.info("area: {}", season.getArea());
//        logger.info("plantingDate: {}", season.getPlantingDate());
//        logger.info("expectedHarvestDate: {}", season.getExpectedHarvestDate());
//        logger.info("status: {}", season.getStatus());

        CropSeason savedSeason = cropSeasonRepository.save(season);
        return cropSeasonMapper.toResponse(savedSeason);
    }

    @Override
    public CropSeasonResponse updateSeason(Long id, CropSeasonRequest request) {
        CropSeason existingSeason = cropSeasonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy mùa vụ với id: " + id));

        // Sử dụng phương thức update của mapper
        CropSeason updatedSeason = cropSeasonMapper.updateEntityFromRequest(existingSeason, request);

        // Lưu và trả về response
        updatedSeason = cropSeasonRepository.save(updatedSeason);
        return cropSeasonMapper.toResponse(updatedSeason);
    }

    @Override
    public void deleteSeason(Long id) {
        if (!cropSeasonRepository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy mùa vụ với ID: " + id);
        }
        cropSeasonRepository.deleteById(id);
    }

    private CropSeason findSeasonById(Long id) {
        return cropSeasonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy mùa vụ với ID: " + id));
    }

    @Override
    public List<Map<String, Object>> getTotalCostsBySeasons() {
        List<CropSeason> seasons = cropSeasonRepository.findAll();
        List<Map<String, Object>> totalCosts = new ArrayList<>();

        for (CropSeason season : seasons) {
            Double totalCost = cropTaskRepository.sumCostBySeasonId(season.getId());
            Map<String, Object> costData = new HashMap<>();
            costData.put("seasonName", season.getSeasonName());
            costData.put("totalCost", totalCost != null ? totalCost : 0.0); // Xử lý null
            totalCosts.add(costData);
        }

        return totalCosts;
    }

}
