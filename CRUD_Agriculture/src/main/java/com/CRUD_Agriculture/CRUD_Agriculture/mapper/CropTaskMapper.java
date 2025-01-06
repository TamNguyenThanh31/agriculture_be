package com.CRUD_Agriculture.CRUD_Agriculture.mapper;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropTask;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropTaskRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropTaskResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CropTaskMapper {

    // Chuyển từ Request sang Entity (thêm mới)
    @Mapping(target = "id", ignore = true) // Không set ID khi tạo mới
    @Mapping(target = "cropSeason", ignore = true) // Không ánh xạ mùa vụ (liên kết trong Service)
    CropTask toEntity(CropTaskRequest request);

    // Chuyển từ Entity sang Response
    CropTaskResponse toResponse(CropTask task);

    // Chuyển danh sách Entity sang danh sách Response
    List<CropTaskResponse> toResponseList(List<CropTask> tasks);

    // Cập nhật Entity từ Request
    @Mapping(target = "id", ignore = true) // Không thay đổi ID
    @Mapping(target = "cropSeason", ignore = true) // Không thay đổi mùa vụ
    CropTask updateEntityFromRequest(
            @MappingTarget CropTask existingEntity,
            CropTaskRequest request
    );
}
