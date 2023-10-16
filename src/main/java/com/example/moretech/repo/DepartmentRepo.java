package com.example.moretech.repo;

import com.example.moretech.models.entities.DepartmentWorkload;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepo extends CrudRepository<DepartmentWorkload, Long> {
}
