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

import com.revature.entities.Spell;
import com.revature.exceptions.SpellNotFoundException;
import com.revature.service.SpellService;
import com.revature.smallentities.SmallSpell;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/spells")
public class SpellController {

	private SpellService spellServ;
	
	@Autowired
	public SpellController(SpellService spellServ) {
		super();
		this.spellServ = spellServ;
	}
	
	/*
	 * Returns a list of all spells, only including the
	 * identifier and name
	 * http://{myurl}/api/spells/all/
	 */
	@GetMapping("/all")
	public ResponseEntity<List<SmallSpell>> getAll() {
		return ResponseEntity.ok(spellServ.getAll());
	}
	
	/*
	 * Returns the specified spell by id or a 400 error if no
	 * spell with the specified id is found
	 * http://{myurl}/api/spells/{id}/
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Spell> getSpellById(@PathVariable("id") int id) {
		try {
			return ResponseEntity.ok(spellServ.getById(id));
		} catch (SpellNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	/*
	 * Returns the specified spell by name or a 400 error if no
	 * spell with the specified name is found
	 * http://{myurl}/api/spells/{name}/
	 */
	@GetMapping("/{name}")
	public ResponseEntity<Spell> getSpellByName(@PathVariable("name") String name) {
		try {
			return ResponseEntity.ok(spellServ.getByName(name));
		} catch (SpellNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	/*
	 * Returns a list of all spells that can be used by the specified
	 * class or a 400 error if no class by the specified name exists
	 * http://{myurl}/api/spells/class/{class_name}/
	 */
	@GetMapping("/class/{class_name}")
	public ResponseEntity<List<Spell>> getSpellsByClassName(@PathVariable("class_name") String className) {
		try {
			return ResponseEntity.ok(spellServ.getByClassName(className));
		} catch (SpellNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	/*
	 * Returns a list of all spells that are from the specified school or
	 * a 400 error if no school by the specified name exists
	 * http://{myurl}/api/spells/school/{school_name}/
	 */
	@GetMapping("/school/{school_name}")
	public ResponseEntity<List<Spell>> getSpellsBySchoolName(@PathVariable("school_name") String schoolName) {
		try {
			return ResponseEntity.ok(spellServ.getBySchoolName(schoolName));
		} catch (SpellNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	/*
	 * Returns a list of all spells that have the specified base level
	 * or a 400 error if the specified level is invalid
	 * http://{myurl}/api/spells/level/{level}/
	 */
	@GetMapping("/level/{level}")
	public ResponseEntity<List<Spell>> getSpellsByLevel(@PathVariable("level") int level) {
		try {
			return ResponseEntity.ok(spellServ.getByLevel(level));
		} catch (SpellNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	/*
	 * Returns a list of all spells that can now be used by the specified class
	 * at the specified level or a 400 error if either value is invalid
	 * http://{myurl}/api/spells/class/{class_name}/level/{level}/
	 */
	@GetMapping("/class/{class_name}/level/{level}")
	public ResponseEntity<List<Spell>> getSpellsByClassAndLevel(@PathVariable("class_name") String className, @PathVariable("level") int level) {
		try {
			return ResponseEntity.ok(spellServ.getByClassAndLevel(className, level));
		} catch (SpellNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
