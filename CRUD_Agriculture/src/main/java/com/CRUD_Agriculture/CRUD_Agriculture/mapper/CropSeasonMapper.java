package com.CRUD_Agriculture.CRUD_Agriculture.mapper;


import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropSeason;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropSeasonRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropSeasonResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CropSeasonMapper {
    CropSeasonResponse toResponse(CropSeason cropSeason);

    CropSeason toEntity(CropSeasonRequest request);
}
