package com.example.moretech.repo;

import com.example.moretech.models.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfficeRepo extends JpaRepository<Office, Long> {

}
