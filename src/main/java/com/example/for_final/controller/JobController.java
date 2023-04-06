package com.example.for_final.controller;

import com.example.for_final.entity.Job;
import com.example.for_final.repository.UserRepo;
import com.example.for_final.search.SearchParams;
import com.example.for_final.security.SecUser;
import com.example.for_final.service.job.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;
    private final UserRepo userRepo;

    @GetMapping
    @PreAuthorize("hasAuthority('JOB_SEEKER') || hasAuthority('COMPANY')")
    public Page<Job> getListOfJobs(SearchParams params) {
        return jobService.getListOfJobs(params);
    }

    @Transactional
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('COMPANY')")
    public Job addJob(@RequestBody Job job, @AuthenticationPrincipal SecUser secUser) {



        job.setCreatedBy(secUser.getUsername());
        job.setUpdatedBy(secUser.getUsername());
        return jobService.addJob(job);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('COMPANY')")
    public void deleteJob(@PathVariable int id, @AuthenticationPrincipal SecUser secUser) {
        jobService.deleteJob(id, secUser);
    }

}
