package com.example.for_final.repository;

import com.example.for_final.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job,Integer>, JpaSpecificationExecutor<Job> {
}
