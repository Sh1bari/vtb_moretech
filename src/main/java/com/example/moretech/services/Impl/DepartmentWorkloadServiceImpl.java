package com.example.moretech.services.Impl;

import com.example.moretech.models.DTO.DepartmentWorkloadRequest;
import com.example.moretech.models.entities.DepartmentWorkload;
import com.example.moretech.repo.DepartmentWorkloadRepo;
import com.example.moretech.services.DepartmentWorkloadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DepartmentWorkloadServiceImpl implements DepartmentWorkloadService {

    private final DepartmentWorkloadRepo departmentWorkloadRepo;

    /**
     * Request type <code>{@link DepartmentWorkloadRequest}</code>
     */
    @Override
    public void saveWorkload(DepartmentWorkloadRequest request, Long id) {
        DepartmentWorkload departmentWorkload = new DepartmentWorkload();
        departmentWorkload.setDepartmentType(request.getDepartmentType());
        departmentWorkload.setWorkload(request.getWorkload());
        departmentWorkload.setTime(LocalDateTime.now());
        departmentWorkload.setDepartmentId(id);
        departmentWorkloadRepo.save(departmentWorkload);
    }
}
