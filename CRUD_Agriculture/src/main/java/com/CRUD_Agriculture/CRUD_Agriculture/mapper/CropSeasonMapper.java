package com.CRUD_Agriculture.CRUD_Agriculture.mapper;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropSeason;
import com.CRUD_Agriculture.CRUD_Agriculture.model.dto.CropSeasonDTO;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropSeasonRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropSeasonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CropSeasonMapper {

    @Mapping(target = "id", ignore = true)
    CropSeason toEntity(CropSeasonRequest request);

    CropSeasonResponse toResponse(CropSeason cropSeason);

    List<CropSeasonResponse> toResponseList(List<CropSeason> cropSeasons);

    List<CropSeason> toEntityList(List<CropSeasonRequest> cropSeasonRequests);
    @Mapping(target = "id", ignore = true)
    CropSeason updateEntityFromRequest(
            @MappingTarget CropSeason existingEntity,
            CropSeasonRequest request
    );
}