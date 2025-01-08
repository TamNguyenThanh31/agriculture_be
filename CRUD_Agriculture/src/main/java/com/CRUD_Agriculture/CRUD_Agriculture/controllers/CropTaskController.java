package com.CRUD_Agriculture.CRUD_Agriculture.controllers;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.enums.TaskStatus;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropTaskRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropTaskResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.services.CropTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")

public class CropTaskController {

    private final CropTaskService cropTaskService;

    @Autowired
    public CropTaskController(CropTaskService cropTaskService) {
        this.cropTaskService = cropTaskService;
    }

    // Thêm công việc mới
    @PostMapping("/{cropSeasonId}")
    public ResponseEntity<CropTaskResponse> addTask(@PathVariable Long cropSeasonId, @RequestBody CropTaskRequest request) {
        CropTaskResponse response = cropTaskService.addTask(cropSeasonId, request);
        return ResponseEntity.ok(response);
    }

    // Lấy danh sách công việc theo mùa vụ
    @GetMapping("/{cropSeasonId}")
    public ResponseEntity<List<CropTaskResponse>> getTasksBySeason(@PathVariable Long cropSeasonId) {
        List<CropTaskResponse> responses = cropTaskService.getTasksBySeason(cropSeasonId);
        return ResponseEntity.ok(responses);
    }

    // Lấy danh sách công việc theo mùa vụ và trạng thái
    @GetMapping("/{cropSeasonId}/status")
    public ResponseEntity<List<CropTaskResponse>> getTasksBySeasonAndStatus(
            @PathVariable Long cropSeasonId,
            @RequestParam String status) {
        List<CropTaskResponse> responses = cropTaskService.getTasksBySeasonAndStatus(cropSeasonId, status);
        return ResponseEntity.ok(responses);
    }

    // Cập nhật công việc
    @PutMapping("/{taskId}")
    public ResponseEntity<CropTaskResponse> updateTask(@PathVariable Long taskId, @RequestBody CropTaskRequest request) {
        CropTaskResponse response = cropTaskService.updateTask(taskId, request);
        return ResponseEntity.ok(response);
    }

    // Cập nhật chi phí công việc
    @PutMapping("/{taskId}/cost")
    public ResponseEntity<CropTaskResponse> updateTaskCost(@PathVariable Long taskId, @RequestParam Double cost) {
        CropTaskResponse response = cropTaskService.updateTaskCost(taskId, cost);
        return ResponseEntity.ok(response);
    }

    // Xóa công việc
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        cropTaskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    // Tính tổng chi phí của một mùa vụ
    @GetMapping("/{cropSeasonId}/total-cost")
    public ResponseEntity<Double> getTotalCostBySeason(@PathVariable Long cropSeasonId) {
        Double totalCost = cropTaskService.getTotalCostBySeason(cropSeasonId);
        return ResponseEntity.ok(totalCost);
    }

    // Lấy công việc gần hạn chót trong một khoảng thời gian
    @GetMapping("/{cropSeasonId}/due-range")
    public ResponseEntity<List<CropTaskResponse>> getTasksDueInRange(
            @PathVariable Long cropSeasonId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<CropTaskResponse> responses = cropTaskService.getTasksDueInRange(cropSeasonId, start, end);
        return ResponseEntity.ok(responses);
    }

    //Lay danh sach task theo trang thai cua tat ca mua vu
    @GetMapping("/status-summary")
    public ResponseEntity<Map<TaskStatus, Long>> getTaskStatusSummary() {
        Map<TaskStatus, Long> statusSummary = cropTaskService.getTaskStatusSummary();
        return ResponseEntity.ok(statusSummary);
    }

    //Lay danh sach theo trang thai cua 1 mua vu
    @GetMapping("/{cropSeasonId}/status-summary")
    public ResponseEntity<Map<TaskStatus, Long>> getTaskStatusSummaryForSeason(@PathVariable Long cropSeasonId) {
        Map<TaskStatus, Long> statusSummary = cropTaskService.getTaskStatusSummaryForSeason(cropSeasonId);
        return ResponseEntity.ok(statusSummary);
    }

}
