package com.revature.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.ClassRepository;

@Service
public class ClassService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ClassRepository classRepo;
	
	@Autowired
	public ClassService(ClassRepository classRepo) {
		super();
		this.classRepo = classRepo;
	}
	
}
