package com.revature.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.SchoolRepository;

@Service
public class SchoolService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private SchoolRepository schoolRepo;
	
	@Autowired
	public SchoolService(SchoolRepository schoolRepo) {
		super();
		this.schoolRepo = schoolRepo;
	}
	
}
