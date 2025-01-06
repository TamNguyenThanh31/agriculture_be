package com.CRUD_Agriculture.CRUD_Agriculture.services.impl;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropSeason;
import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropTask;
import com.CRUD_Agriculture.CRUD_Agriculture.exception.ResourceNotFoundException;
import com.CRUD_Agriculture.CRUD_Agriculture.mapper.CropTaskMapper;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropTaskRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropTaskResponse;
import com.CRUD_Agriculture.CRUD_Agriculture.repository.CropSeasonRepository;
import com.CRUD_Agriculture.CRUD_Agriculture.repository.CropTaskRepository;
import com.CRUD_Agriculture.CRUD_Agriculture.services.CropTaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CropTaskServiceImpl implements CropTaskService {

    private final CropTaskRepository cropTaskRepository;
    private final CropSeasonRepository cropSeasonRepository;
    private final CropTaskMapper cropTaskMapper;

    public CropTaskServiceImpl(CropTaskRepository cropTaskRepository, CropSeasonRepository cropSeasonRepository, CropTaskMapper cropTaskMapper) {
        this.cropTaskRepository = cropTaskRepository;
        this.cropSeasonRepository = cropSeasonRepository;
        this.cropTaskMapper = cropTaskMapper;
    }

    // Thêm công việc mới
    @Override
    public CropTaskResponse addTask(Long cropSeasonId, CropTaskRequest request) {
        CropSeason cropSeason = cropSeasonRepository.findById(cropSeasonId)
                .orElseThrow(() -> new ResourceNotFoundException("Mùa vụ không tồn tại với ID: " + cropSeasonId));
        CropTask task = cropTaskMapper.toEntity(request);
        task.setCropSeason(cropSeason);
        return cropTaskMapper.toResponse(cropTaskRepository.save(task));
    }

    @Override
    public List<CropTaskResponse> getTasksBySeason(Long cropSeasonId) {
        List<CropTask> tasks = cropTaskRepository.findByCropSeasonId(cropSeasonId);
        return cropTaskMapper.toResponseList(tasks);
    }

    // Cập nhật công việc
    @Override
    public CropTaskResponse updateTask(Long taskId, CropTaskRequest request) {
        CropTask existingTask = cropTaskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Công việc không tồn tại với ID: " + taskId));
        cropTaskMapper.updateEntityFromRequest(existingTask, request);
        return cropTaskMapper.toResponse(cropTaskRepository.save(existingTask));
    }

    @Override
    public List<CropTaskResponse> getTasksBySeasonAndStatus(Long cropSeasonId, String status) {
        List<CropTask> tasks = cropTaskRepository.findByCropSeasonIdAndStatus(cropSeasonId, status);
        return cropTaskMapper.toResponseList(tasks);
    }

    // Cập nhật chi phí công việc
    @Override
    public CropTaskResponse updateTaskCost(Long taskId, Double cost) {
        CropTask task = cropTaskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Công việc không tồn tại với ID: " + taskId));
        task.setCost(cost);
        return cropTaskMapper.toResponse(cropTaskRepository.save(task));
    }

    @Override
    public void deleteTask(Long taskId) {
        if (!cropTaskRepository.existsById(taskId)) {
            throw new ResourceNotFoundException("Công việc không tồn tại với ID: " + taskId);
        }
        cropTaskRepository.deleteById(taskId);
    }

    // Tổng chi phí theo mùa vụ
    @Override
    public Double getTotalCostBySeason(Long cropSeasonId) {
        List<CropTask> tasks = cropTaskRepository.findByCropSeasonId(cropSeasonId);
        return tasks.stream()
                .filter(task -> task.getCost() != null)
                .mapToDouble(CropTask::getCost)
                .sum();
    }

    // Lấy công việc theo khoảng thời gian gần hạn chót
    @Override
    public List<CropTaskResponse> getTasksDueInRange(Long cropSeasonId, LocalDate startDate, LocalDate endDate) {
        List<CropTask> tasks = cropTaskRepository.findByDueDateBetweenAndCropSeasonIdOrderByDueDateAsc(
                startDate, endDate, cropSeasonId);
        return cropTaskMapper.toResponseList(tasks);
    }
}
