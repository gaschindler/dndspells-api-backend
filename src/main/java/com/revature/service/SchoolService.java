package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.SchoolRepository;
import com.revature.entities.School;
import com.revature.exceptions.SchoolNotFoundException;

@Service
public class SchoolService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private SchoolRepository schoolRepo;
	
	@Autowired
	public SchoolService(SchoolRepository schoolRepo) {
		super();
		this.schoolRepo = schoolRepo;
	}
	
	@Transactional(readOnly=true)
	public List<School> getAll() {
		log.info("Returning a list of all available schools");
		return schoolRepo.findAll();
	}
	
	@Transactional(readOnly=true)
	public School getById(int id) throws SchoolNotFoundException {
		// id must be greater than 0
		if (id <= 0) {
			log.warn("School ID given was invalid. ID passed was: {}. Returning a 400 error", id);
			throw new SchoolNotFoundException("Invalid school ID. ID must be greater than 0.");
		}
		
		Optional<School> returnSchool = schoolRepo.findById(id);
		
		if (!returnSchool.isPresent()) {
			log.warn("ID given not associated with a school in database. ID passed was: {}. Returning a 400 error", id);
			throw new SchoolNotFoundException("Invalid school ID. ID not in database.");
		}
		
		log.info("School with the ID: {} located in database. Returning this school");
		return returnSchool.get();
	}
	
	@Transactional(readOnly=true)
	public School getByName(String name) throws SchoolNotFoundException {
		// make name lower case to match database
		name = name.toLowerCase();
		
		// name must not be blank
		if (name.equals("")) {
			log.warn("School name given was invalid. Name passed was: \"{}\". Returning a 400 error", name);
			throw new SchoolNotFoundException("Invalid school name. Name must not be blank.");
		}
		
		Optional<School> returnSchool = schoolRepo.findByName(name);
		
		if (!returnSchool.isPresent()) {
			log.warn("Name given not associated with a school in database. Name passed was: \"{}\". Returning a 400 error", name);
			throw new SchoolNotFoundException("Invalid school name. Name not in database.");
		}
		
		log.info("School with the name: {} located in database. Returning this school");
		return returnSchool.get();
	}
	
}
