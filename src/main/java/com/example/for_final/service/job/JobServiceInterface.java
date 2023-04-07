package com.example.for_final.service.job;

import com.example.for_final.entity.Job;
import com.example.for_final.search.SearchParams;
import com.example.for_final.security.SecUser;
import org.springframework.data.domain.Page;

public interface JobServiceInterface {
    Page<Job> getListOfJobs(SearchParams params);
    Job addJob(Job job);


    String deleteJob(int id, SecUser secUser);
}
