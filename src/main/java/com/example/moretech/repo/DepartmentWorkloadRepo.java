package com.example.moretech.repo;

import com.example.moretech.models.DTO.IdWorkloadDto;
import com.example.moretech.models.entities.DepartmentWorkload;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface DepartmentWorkloadRepo extends CrudRepository<DepartmentWorkload, Long> {
    @Query("SELECT new com.example.moretech.models.DTO.IdWorkloadDto(dw.departmentId, dw.workload) FROM DepartmentWorkload dw " +
            "WHERE dw.departmentId = :id " +
            "AND dw.time BETWEEN :sTime AND :eTime ")
    List<IdWorkloadDto> findDepartmentWorkloadsByIdAndTimeRange(
            @Param("id") Long id,
            @Param("sTime") LocalDateTime sTime,
            @Param("eTime") LocalDateTime eTime
    );
}
