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
//            if(params.getIndustry() != null) {
//                predicate = cb.and(predicate, cb.equal(root.get(Job_.INDUSTRY), params.getIndustry()));
//            }
//            if(params.getJobType() != null) {
//                predicate = cb.and(predicate, cb.equal(root.get(Job_.JOB_TYPE), params.getJobType()));
//            }


            return predicate;
        }, PageRequest.of(params.getPage(), params.getPageSize()));
    }

    @Override
    public Job addJob(Job job) {

//        var user = userRepo.findByEmail(secUser.getUsername()).
//                orElseThrow(() -> new NotFoundException("User Not Found"));
//        var companyName = user.getCompanies();
        job.setCompany(companyRepo.findCompanyByCompanyName(job.getCompanyName()));
        return jobRepo.save(job);
    }
    @Override
    public void deleteJob(int id, SecUser secUser) {

        var user = userRepo.findByEmail(secUser.getUsername())
                .orElseThrow(() -> new NotFoundException("User Not Found"));

        var job = jobRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Job Not Found"));

        System.out.printf("User: %s%n", user.getCompanies().toString());

        System.out.printf("Job Company Name: %s%n", job.getCompanyName());

        boolean isOwner;

//        user.getCompanies().stream().map(company -> {
//            if(job.getCompanyName() == company.getCompanyName()){
//                isOwner.set(true);
//            }
//            return false;
//        });

//        System.out.printf(String.valueOf(isOwner));

//        if(isOwner){
//            jobRepo.delete(jobRepo.findById(id).orElseThrow(() -> new NotFoundException("Job Not Found")));
//        }
    }
}
