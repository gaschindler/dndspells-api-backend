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

import com.revature.entities.Class;
import com.revature.service.ClassService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/classes")
public class ClassController {

	private ClassService classServ;
	
	@Autowired
	public ClassController(ClassService classServ) {
		super();
		this.classServ = classServ;
	}
	
	/*
	 * Returns a list of all available classes
	 * http://{myurl}/api/classes/
	 */
	@GetMapping
	public ResponseEntity<List<Class>> getAll() {
		return ResponseEntity.ok(classServ.getAll());
	}
	
	/*
	 * Returns the specified class by id or a 400 error if no class
	 * with the specified id is found
	 * http://{myurl}/api/classes/{id}/
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Class> getClassById(@PathVariable("id") int id) {
		try {
			return ResponseEntity.ok(classServ.getById(id));
		} catch (ClassNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	/*
	 * Returns the specified class by name or a 400 error if no class
	 * with the specified name is found
	 * http://{myurl}/api/classes/{name}/
	 */
	@GetMapping("/{name}")
	public ResponseEntity<Class> getClassByName(@PathVariable("name") String name) {
		try {
			return ResponseEntity.ok(classServ.getByName(name));
		} catch (ClassNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
