package com.example.moretech.repo;

import com.example.moretech.models.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfR extends JpaRepository<Office, Long> {
    @Query(value = "SELECT * FROM office " +
            "WHERE ST_DWithin(ST_MakePoint(:longitude, :latitude)::geography, ST_MakePoint(office.longitude, office.latitude)::geography, :radius * 1000)",
            nativeQuery = true)
    List<Office> findOfficesInRadius(@Param("longitude") double longitude, @Param("latitude") double latitude, @Param("radius") double radius);
}
