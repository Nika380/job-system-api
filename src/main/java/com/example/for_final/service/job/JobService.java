package com.example.for_final.service.job;

import com.example.for_final.entity.Job;
import com.example.for_final.repository.CompanyRepo;
import com.example.for_final.repository.JobRepo;
import com.example.for_final.repository.UserRepo;
import com.example.for_final.search.SearchParams;
import com.example.for_final.security.SecUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import javax.persistence.criteria.Predicate;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class JobService implements JobServiceInterface{
    private final JobRepo jobRepo;
    private final CompanyRepo companyRepo;
    private final UserRepo userRepo;

    @Override
    public Page<Job> getListOfJobs(SearchParams params) {
        return jobRepo.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(params.getIndustry() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("industry"), params.getIndustry()));
            }
            if(params.getJobType() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("jobType"), params.getJobType()));
            }


            return predicate;
        }, PageRequest.of(params.getPage(), params.getPageSize()));
    }

    @Override
    public Job addJob(Job job) {

        job.setCompany(companyRepo.findCompanyByCompanyName(job.getCompanyName()).
                orElseThrow(() -> new NotFoundException("Company Not Found")));
        return jobRepo.save(job);
    }
    @Override
    public String deleteJob(int id, SecUser secUser) {

        var user = userRepo.findByEmail(secUser.getUsername())
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        var job = jobRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Job Not Found"));


        boolean isOwner = user.getCompanies().stream()
                .anyMatch(company -> company.getCompanyName().equals(job.getCompanyName()));

        if(isOwner) {
            jobRepo.delete(job);
            return "Job Deleted";
        } else {
            return "Job Not Deleted";
        }

    }
}
