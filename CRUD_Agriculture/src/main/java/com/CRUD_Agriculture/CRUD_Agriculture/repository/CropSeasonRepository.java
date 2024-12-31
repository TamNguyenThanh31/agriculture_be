package com.CRUD_Agriculture.CRUD_Agriculture.repository;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.CropSeason;
import com.CRUD_Agriculture.CRUD_Agriculture.entity.enums.SeasonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropSeasonRepository extends JpaRepository<CropSeason, Long> {
    List<CropSeason> findByStatus(SeasonStatus status);
    List<CropSeason> findByCropType(String cropType);
}
