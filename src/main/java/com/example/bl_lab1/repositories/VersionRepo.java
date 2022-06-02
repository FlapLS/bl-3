package com.example.bl_lab1.repositories;

import com.example.bl_lab1.model.SectionEntity;
import com.example.bl_lab1.model.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VersionRepo extends JpaRepository<VersionEntity, Integer> {
    List<VersionEntity> findAllByStatus(String status);
    List<VersionEntity> findAllBySectionId(Integer sectionId);
    List<VersionEntity> findAllBySectionIdAndStatus(Integer sectionId, String status);
    @Transactional
    void deleteAllByStatus(String status);
}
