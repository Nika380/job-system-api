package com.example.for_final.service.job;

import com.example.for_final.entity.Job;
import com.example.for_final.search.SearchParams;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobServiceInterface {
    Page<Job> getListOfJobs(SearchParams params);
    Job addJob(Job job);
}
