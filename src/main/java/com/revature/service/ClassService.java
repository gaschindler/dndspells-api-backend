package com.revature.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.ClassRepository;
import com.revature.entities.Class;

@Service
public class ClassService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private ClassRepository classRepo;
	
	@Autowired
	public ClassService(ClassRepository classRepo) {
		super();
		this.classRepo = classRepo;
	}
	
	@Transactional(readOnly=true)
	public List<Class> getAll() {
		log.info("Returning a list of all available classes");
		return classRepo.findAll();
	}
	
	@Transactional(readOnly=true)
	public Class getById(int id) throws ClassNotFoundException {
		// id must be greater than 0
		if (id <= 0) {
			log.warn("ID given was invalid. ID passed was: {}. Returning a 400 error", id);
			throw new ClassNotFoundException("Invalid ID. ID must be greater than 0.");
		}
		
		Optional<Class> returnClass = classRepo.findById(id);
		
		if (!returnClass.isPresent()) {
			log.warn("ID given not associated with a class in database. ID passed was: {}. Returning a 400 error", id);
			throw new ClassNotFoundException("Invalid ID. ID not in database.");
		}
		
		log.info("Class with the ID: {} located in database. Returning this class");
		return returnClass.get();
	}
	
	@Transactional(readOnly=true)
	public Class getByName(String name) throws ClassNotFoundException {
		// name must not be blank
		if (name.equals("")) {
			log.warn("Name given was invalid. Name passed was: \"{}\". Returning a 400 error", name);
			throw new ClassNotFoundException("Invalid name. Name must not be blank.");
		}
		
		Optional<Class> returnClass = classRepo.findByName(name);
		
		if (!returnClass.isPresent()) {
			log.warn("Name given not associated with a class in database. Name passed was: \"{}\". Returning a 400 error", name);
			throw new ClassNotFoundException("Invalid name. Name not in database.");
		}
		
		log.info("Class with the name: {} located in database. Returning this class");
		return returnClass.get();
	}
	
}
