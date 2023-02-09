package com.example.for_final;

import com.example.for_final.RoleAndTypes.JobIndustry;
import com.example.for_final.RoleAndTypes.JobType;
import com.example.for_final.entity.Job;
import com.example.for_final.service.job.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
@Transactional
class ForFinalApplicationTests {
	@Autowired
	private EntityManager em;
	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() {
	}

}
