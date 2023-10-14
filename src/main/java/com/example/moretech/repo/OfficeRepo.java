package com.example.moretech.repo;

import com.example.moretech.models.entities.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfficeRepo extends CrudRepository<Office, Long> {
    List<Office> findByOpenHours_daysAndOpenHours_hours(String day, String hours);
}
