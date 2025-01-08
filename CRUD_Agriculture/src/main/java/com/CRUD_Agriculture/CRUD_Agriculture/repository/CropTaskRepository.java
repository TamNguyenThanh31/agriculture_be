package com.CRUD_Agriculture.CRUD_Agriculture.repository;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropTask;
import com.CRUD_Agriculture.CRUD_Agriculture.model.request.CropTaskRequest;
import com.CRUD_Agriculture.CRUD_Agriculture.model.response.CropTaskResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    // Lay tong gia tien theo tung mua vu
    @Query("SELECT SUM(t.cost) FROM CropTask t WHERE t.cropSeason.id = :seasonId")
    Double sumCostBySeasonId(@Param("seasonId") Long seasonId);

    //Lay trang thai theo tung mua vu
    @Query("SELECT t.status, COUNT(t) FROM CropTask t WHERE t.cropSeason.id = :seasonId GROUP BY t.status")
    List<Object[]> countTasksByStatusForSeason(@Param("seasonId") Long seasonId);


    //Lay trang thai theo ca mua vu
    @Query("SELECT t.status, COUNT(t) FROM CropTask t GROUP BY t.status")
    List<Object[]> countTasksByStatus();

}
