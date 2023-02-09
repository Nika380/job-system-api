package com.example.for_final.entity;

import com.example.for_final.RoleAndTypes.JobIndustry;
import com.example.for_final.RoleAndTypes.JobType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data
@Table(name = "jobs")
public class Job {
    public Job(JobType jobType, JobIndustry industry, String companyName) {
        this.jobType = jobType;
        this.industry = industry;
        this.companyName = companyName;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "job_type")
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    @Basic
    @Column(name = "industry")
    @Enumerated(EnumType.STRING)
    private JobIndustry industry;
    @Column(name = "company_name")
    private String companyName;
    @Basic
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Basic
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Basic
    @Column(name = "created_by")
    private String createdBy;
    @Basic
    @Column(name = "updated_by")
    private String updatedBy;
    @PrePersist
    void prePersist() {
        updatedAt = LocalDateTime.now();
        createdAt = LocalDateTime.now();
        createdBy = "nika";
        updatedBy = "nika";
    }
    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "company_id")
    private Company company;
}
