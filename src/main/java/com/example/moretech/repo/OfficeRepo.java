package com.example.moretech.repo;

import com.example.moretech.models.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfficeRepo extends CrudRepository<Office, Long>, JpaSpecificationExecutor<Office> {
    List<Office> findByOpenHours_daysAndOpenHours_hours(String day, String hours);
}
