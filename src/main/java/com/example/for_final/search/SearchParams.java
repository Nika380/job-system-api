package com.example.for_final.search;

import com.example.for_final.RoleAndTypes.JobIndustry;
import com.example.for_final.RoleAndTypes.JobType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchParams {
    private String companyName;
    private String username;
    private JobIndustry industry;
    private JobType jobType;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Integer pageSize = 5;
    private Integer page = 0;
}
