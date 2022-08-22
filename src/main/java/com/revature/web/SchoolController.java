package com.revature.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.entities.School;
import com.revature.exceptions.SchoolNotFoundException;
import com.revature.service.SchoolService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/schools")
public class SchoolController {

	private SchoolService schoolServ;
	
	@Autowired
	public SchoolController(SchoolService schoolServ) {
		super();
		this.schoolServ = schoolServ;
	}
	
	/*
	 * Returns a list of all available schools
	 * http://{myurl}/api/schools/
	 */
	@GetMapping
	public ResponseEntity<List<School>> getAll() {
		return ResponseEntity.ok(schoolServ.getAll());
	}
	
	/*
	 * Returns the specified school by id or a 400 error if no school
	 * with the specified id is found
	 * http://{myurl}/api/schools/{id}/
	 */
	@GetMapping("/{id}")
	public ResponseEntity<School> getSchoolById(@PathVariable("id") int id) {
		try {
			return ResponseEntity.ok(schoolServ.getById(id));
		} catch (SchoolNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	/*
	 * Returns the specified school by name or a 400 error if no school
	 * with the specified name is found
	 * http://{myurl}/api/schools/{name}/
	 */
	@GetMapping("/{name}")
	public ResponseEntity<School> getSchoolByName(@PathVariable("name") String name) {
		try {
			return ResponseEntity.ok(schoolServ.getByName(name));
		} catch (SchoolNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
