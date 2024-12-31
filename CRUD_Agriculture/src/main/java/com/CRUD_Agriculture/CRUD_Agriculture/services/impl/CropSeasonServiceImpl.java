package com.CRUD_Agriculture.CRUD_Agriculture.services.impl;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropSeason;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropSeasonRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropSeasonResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.repository.CropSeasonRepository;
import com.CRUD_Agriculture.CRUD_Agriculture.services.CropSeasonService;
import com.CRUD_Agriculture.CRUD_Agriculture.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
@Transactional
public class CropSeasonServiceImpl implements CropSeasonService {

    private final CropSeasonRepository cropSeasonRepository;

    // Constructor thủ công
    public CropSeasonServiceImpl(CropSeasonRepository cropSeasonRepository) {
        this.cropSeasonRepository = cropSeasonRepository;
    }

    @Override
    public List<CropSeasonResponse> getAllSeasons() {
        List<CropSeason> seasons = cropSeasonRepository.findAll();
        List<CropSeasonResponse> responses = new ArrayList<>();
        for (CropSeason season : seasons) {
            responses.add(convertToResponse(season));
        }

        return responses;
    }

    @Override
    public CropSeasonResponse getSeasonById(Long id) {
        CropSeason season = findSeasonById(id);
        return convertToResponse(season);
    }

    @Override
    public CropSeasonResponse createSeason(CropSeasonRequest request) {
        CropSeason season = new CropSeason();
        BeanUtils.copyProperties(request, season);
        CropSeason savedSeason = cropSeasonRepository.save(season);
        return convertToResponse(savedSeason);
    }

    @Override
    public CropSeasonResponse updateSeason(Long id, CropSeasonRequest request) {
        CropSeason existingSeason = findSeasonById(id);
        BeanUtils.copyProperties(request, existingSeason);
        CropSeason updatedSeason = cropSeasonRepository.save(existingSeason);
        return convertToResponse(updatedSeason);
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

    private CropSeasonResponse convertToResponse(CropSeason season) {
        CropSeasonResponse response = new CropSeasonResponse();
        BeanUtils.copyProperties(season, response);
        return response;
    }
}
