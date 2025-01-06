package com.CRUD_Agriculture.CRUD_Agriculture.services;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropTask;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropTaskRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropTaskResponse;

import java.time.LocalDate;
import java.util.List;

public interface CropTaskService {
    // Thêm công việc
    CropTaskResponse addTask(Long cropSeasonId, CropTaskRequest request);

    // Lấy danh sách công việc theo mùa vụ
    List<CropTaskResponse> getTasksBySeason(Long cropSeasonId);

    // Lấy danh sách công việc theo trạng thái
    List<CropTaskResponse> getTasksBySeasonAndStatus(Long cropSeasonId, String status);

    // Cập nhật công việc
    CropTaskResponse updateTask(Long taskId, CropTaskRequest request);

    // Cập nhật chi phí
    CropTaskResponse updateTaskCost(Long taskId, Double cost);

    // Xóa công việc
    void deleteTask(Long taskId);

    // Tính tổng chi phí mùa vụ
    Double getTotalCostBySeason(Long cropSeasonId);

    // Lấy công việc theo thời gian gần hạn
    List<CropTaskResponse> getTasksDueInRange(Long cropSeasonId, LocalDate startDate, LocalDate endDate);
}
