package com.CRUD_Agriculture.CRUD_Agriculture.mapper;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropSeason;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropSeasonRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropSeasonResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-02T13:47:03+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CropSeasonMapperImpl implements CropSeasonMapper {

    @Override
    public CropSeason toEntity(CropSeasonRequest request) {
        if ( request == null ) {
            return null;
        }

        CropSeason cropSeason = new CropSeason();

        cropSeason.setSeasonName( request.getSeasonName() );
        cropSeason.setCropType( request.getCropType() );
        cropSeason.setArea( request.getArea() );
        cropSeason.setPlantingDate( request.getPlantingDate() );
        cropSeason.setExpectedHarvestDate( request.getExpectedHarvestDate() );
        cropSeason.setStatus( request.getStatus() );

        return cropSeason;
    }

    @Override
    public CropSeasonResponse toResponse(CropSeason cropSeason) {
        if ( cropSeason == null ) {
            return null;
        }

        CropSeasonResponse cropSeasonResponse = new CropSeasonResponse();

        cropSeasonResponse.setId( cropSeason.getId() );
        cropSeasonResponse.setSeasonName( cropSeason.getSeasonName() );
        cropSeasonResponse.setCropType( cropSeason.getCropType() );
        cropSeasonResponse.setArea( cropSeason.getArea() );
        cropSeasonResponse.setPlantingDate( cropSeason.getPlantingDate() );
        cropSeasonResponse.setExpectedHarvestDate( cropSeason.getExpectedHarvestDate() );
        cropSeasonResponse.setStatus( cropSeason.getStatus() );
        cropSeasonResponse.setCreatedAt( cropSeason.getCreatedAt() );
        cropSeasonResponse.setUpdatedAt( cropSeason.getUpdatedAt() );

        return cropSeasonResponse;
    }

    @Override
    public List<CropSeasonResponse> toResponseList(List<CropSeason> cropSeasons) {
        if ( cropSeasons == null ) {
            return null;
        }

        List<CropSeasonResponse> list = new ArrayList<CropSeasonResponse>( cropSeasons.size() );
        for ( CropSeason cropSeason : cropSeasons ) {
            list.add( toResponse( cropSeason ) );
        }

        return list;
    }

    @Override
    public List<CropSeason> toEntityList(List<CropSeasonRequest> cropSeasonRequests) {
        if ( cropSeasonRequests == null ) {
            return null;
        }

        List<CropSeason> list = new ArrayList<CropSeason>( cropSeasonRequests.size() );
        for ( CropSeasonRequest cropSeasonRequest : cropSeasonRequests ) {
            list.add( toEntity( cropSeasonRequest ) );
        }

        return list;
    }

    @Override
    public CropSeason updateEntityFromRequest(CropSeason existingEntity, CropSeasonRequest request) {
        if ( request == null ) {
            return existingEntity;
        }

        existingEntity.setSeasonName( request.getSeasonName() );
        existingEntity.setCropType( request.getCropType() );
        existingEntity.setArea( request.getArea() );
        existingEntity.setPlantingDate( request.getPlantingDate() );
        existingEntity.setExpectedHarvestDate( request.getExpectedHarvestDate() );
        existingEntity.setStatus( request.getStatus() );

        return existingEntity;
    }
}
