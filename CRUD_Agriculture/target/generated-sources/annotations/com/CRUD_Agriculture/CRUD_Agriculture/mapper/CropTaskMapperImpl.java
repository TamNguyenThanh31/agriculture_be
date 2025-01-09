package com.CRUD_Agriculture.CRUD_Agriculture.mapper;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropTask;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropTaskRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropTaskResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-09T15:40:51+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class CropTaskMapperImpl implements CropTaskMapper {

    @Override
    public CropTask toEntity(CropTaskRequest request) {
        if ( request == null ) {
            return null;
        }

        CropTask cropTask = new CropTask();

        cropTask.setTaskName( request.getTaskName() );
        cropTask.setDueDate( request.getDueDate() );
        cropTask.setStatus( request.getStatus() );
        cropTask.setNotes( request.getNotes() );
        cropTask.setCost( request.getCost() );

        return cropTask;
    }

    @Override
    public CropTaskResponse toResponse(CropTask task) {
        if ( task == null ) {
            return null;
        }

        CropTaskResponse cropTaskResponse = new CropTaskResponse();

        cropTaskResponse.setId( task.getId() );
        cropTaskResponse.setTaskName( task.getTaskName() );
        cropTaskResponse.setDueDate( task.getDueDate() );
        cropTaskResponse.setStatus( task.getStatus() );
        cropTaskResponse.setNotes( task.getNotes() );
        cropTaskResponse.setCost( task.getCost() );

        return cropTaskResponse;
    }

    @Override
    public List<CropTaskResponse> toResponseList(List<CropTask> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<CropTaskResponse> list = new ArrayList<CropTaskResponse>( tasks.size() );
        for ( CropTask cropTask : tasks ) {
            list.add( toResponse( cropTask ) );
        }

        return list;
    }

    @Override
    public CropTask updateEntityFromRequest(CropTask existingEntity, CropTaskRequest request) {
        if ( request == null ) {
            return existingEntity;
        }

        existingEntity.setTaskName( request.getTaskName() );
        existingEntity.setDueDate( request.getDueDate() );
        existingEntity.setStatus( request.getStatus() );
        existingEntity.setNotes( request.getNotes() );
        existingEntity.setCost( request.getCost() );

        return existingEntity;
    }
}
