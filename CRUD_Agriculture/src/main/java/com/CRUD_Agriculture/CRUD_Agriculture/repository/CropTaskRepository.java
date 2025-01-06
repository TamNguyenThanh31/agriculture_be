package com.CRUD_Agriculture.CRUD_Agriculture.repository;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropTask;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropTaskRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropTaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CropTaskRepository extends JpaRepository<CropTask, Long> {

    // Lấy danh sách công việc theo ID mùa vụ
    List<CropTask> findByCropSeasonId(Long cropSeasonId);

    // Lấy danh sách công việc theo trạng thái
    List<CropTask> findByCropSeasonIdAndStatus(Long cropSeasonId, String status);

    // Tìm công việc theo tên (nếu cần)
    List<CropTask> findByTaskNameContainingIgnoreCase(String taskName);

    // Tìm công việc gần hạn chót (nếu cần)
    List<CropTask> findByDueDateBetweenAndCropSeasonIdOrderByDueDateAsc(
            LocalDate startDate,
            LocalDate endDate,
            Long cropSeasonId
    );
}
