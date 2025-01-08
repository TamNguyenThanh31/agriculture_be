package com.CRUD_Agriculture.CRUD_Agriculture.services;

import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropSeasonRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropSeasonResponse;

import java.util.List;
import java.util.Map;


public interface CropSeasonService {
    List<CropSeasonResponse> getAllSeasons();
    CropSeasonResponse getSeasonById(Long id);
    CropSeasonResponse createSeason(CropSeasonRequest request);
    CropSeasonResponse updateSeason(Long id, CropSeasonRequest request);
    void deleteSeason(Long id);

    List<Map<String, Object>> getTotalCostsBySeasons();
}
