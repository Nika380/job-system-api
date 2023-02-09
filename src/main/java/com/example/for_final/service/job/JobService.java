package com.example.for_final.service.job;

import com.example.for_final.entity.Company;
import com.example.for_final.entity.Job;
import com.example.for_final.entity.Job_;
import com.example.for_final.repository.CompanyRepo;
import com.example.for_final.repository.JobRepo;
import com.example.for_final.search.SearchParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService implements JobServiceInterface{
    private final JobRepo jobRepo;
    private final CompanyRepo companyRepo;

    @Override
    public Page<Job> getListOfJobs(SearchParams params) {
        return jobRepo.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(params.getIndustry() != null) {
                predicate = cb.and(predicate, cb.equal(root.get(Job_.INDUSTRY), params.getIndustry()));
            }
            if(params.getJobType() != null) {
                predicate = cb.and(predicate, cb.equal(root.get(Job_.JOB_TYPE), params.getJobType()));
            }


            return predicate;
        }, PageRequest.of(params.getPage(), params.getPageSize()));
    }

    @Override
    public Job addJob(Job job) {
        job.setCompany(companyRepo.findCompanyByCompanyName(job.getCompanyName()));
        return jobRepo.save(job);
    }
}
