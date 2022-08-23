package com.revature.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.ClassRepository;
import com.revature.data.SchoolRepository;
import com.revature.data.SpellRepository;
import com.revature.entities.Class;
import com.revature.entities.School;
import com.revature.entities.Spell;
import com.revature.exceptions.SpellNotFoundException;
import com.revature.smallentities.SmallSpell;

@Service
public class SpellService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private SpellRepository spellRepo;
	private ClassRepository classRepo;
	private SchoolRepository schoolRepo;
	
	@Autowired
	public SpellService(SpellRepository spellRepo, ClassRepository classRepo, SchoolRepository schoolRepo) {
		super();
		this.spellRepo = spellRepo;
		this.classRepo = classRepo;
		this.schoolRepo = schoolRepo;
	}
	
	@Transactional(readOnly=true)
	public List<SmallSpell> getAll() {
		log.info("Returning a list of all available spells");
		
		List<SmallSpell> returnSpells = new LinkedList<>();
		List<Spell> spellList = spellRepo.findAll();
		
		for (Spell spell : spellList) {
			SmallSpell newSpell = new SmallSpell(spell.getId(), spell.getName());
			returnSpells.add(newSpell);
		}
		
		return returnSpells;
	}
	
	@Transactional(readOnly=true)
	public Spell getById(int id) throws SpellNotFoundException {
		// id must be greater than 0
		if (id <= 0) {
			log.warn("Spell ID given was invalid. ID passed was: {}. Returning a 400 error", id);
			throw new SpellNotFoundException("Invalid spell ID. ID must be greater than 0.");
		}
		
		Optional<Spell> returnSpell = spellRepo.findById(id);
		
		if(!returnSpell.isPresent()) {
			log.warn("ID given not associated with a spell in database. ID passed was: {}. Returning a 400 error", id);
			throw new SpellNotFoundException("Invalid spell ID. ID not in database.");
		}
		
		log.info("Spell with the ID: {} located in database. Returning this spell");
		return returnSpell.get();
	}
	
	@Transactional(readOnly=true)
	public Spell getByName(String name) throws SpellNotFoundException {
		// make name lower case to match database
		name = name.toLowerCase();
		
		// replace '-' and '_' with ' '
		name = name.replace('-', ' ');
		name = name.replace('_', ' ');
		
		// name must not be blank
		if (name.equals("")) {
			log.warn("Spell name given was invalid. Name passed was: \"{}\". Returning a 400 error", name);
			throw new SpellNotFoundException("Invalid spell name. Name must not be blank.");
		}
		
		Optional<Spell> returnSpell = spellRepo.findByName(name);
		
		if (!returnSpell.isPresent()) {
			log.warn("Name given not associated with a spell in database. Name passed was: \"{}\". Returning a 400 error", name);
			throw new SpellNotFoundException("Invalid spell name. Name not in database.");
		}
		
		log.info("Spell with the name: {} located in database. Returning this spell");
		return returnSpell.get();
	}
	
	@Transactional(readOnly=true)
	public List<Spell> getByClassName(String name) throws SpellNotFoundException {
		// make name lower case to match database
		name = name.toLowerCase();
		
		// name must not be blank
		if (name.equals("")) {
			log.warn("Class name given was invalid. Name passed was: \"{}\". Returning a 400 error", name);
			throw new SpellNotFoundException("Invalid class name. Name must not be blank.");
		}
		
		Optional<Class> queryClass = classRepo.findByName(name);
		
		if (!queryClass.isPresent()) {
			log.warn("Name given not associated with a class in database. Name passed was: \"{}\". Returning a 400 error", name);
			throw new SpellNotFoundException("Invalid class name. Name not in database");
		}
		
		return queryClass.get().getSpells();
	}
	
	@Transactional(readOnly=true)
	public List<Spell> getBySchoolName(String name) throws SpellNotFoundException {
		// make name lower case to match database
		name = name.toLowerCase();
		
		// name must not be blank
		if (name.equals("")) {
			log.warn("School name given was invalid. Name passed was: \"{}\". Returning a 400 error", name);
			throw new SpellNotFoundException("Invalid school name. Name must not be blank.");
		}
		
		Optional<School> querySchool = schoolRepo.findByName(name);
		
		if (!querySchool.isPresent()) {
			log.warn("Name given not associted with a school in database. Name passed was: \"{}\". Returning a 400 error", name);
			throw new SpellNotFoundException("Invalid school name. Name not in database");
		}
		
		return querySchool.get().getSpells();
	}
	
	@Transactional(readOnly=true)
	public List<Spell> getByLevel(int level) throws SpellNotFoundException {
		// level must be in the range [0, 9] inclusive
		if (level < 0 || level > 9) {
			log.warn("Level given was invalid. Level passed was: {}. Returning a 400 error", level);
			throw new SpellNotFoundException("Invalid level. Level must be between 0 and 9 inclusive.");
		}
		
		return spellRepo.findByLevel(level);
	}
	
	@Transactional(readOnly=true)
	public List<Spell> getByClassAndLevel(String name, int level) throws SpellNotFoundException {
		// make name lower case to match database
		name = name.toLowerCase();
		
		// name must not be blank
		if (name.equals("")) {
			log.warn("Class name given was invalid. Name passed was: \" {}\". Returning a 400 error", name);
			throw new SpellNotFoundException("Invalid class name. Name must not be blank.");
		}
		
		// level must be in the range [0, 9] inclusive
		if (level < 0 || level > 9) {
			log.warn("Level given was invalid. Level passed was: {}. Returning a 400 error", level);
			throw new SpellNotFoundException("Invalid level. Level must be between 0 and 9 inclusive.");
		}
		
		Optional<Class> queryClass = classRepo.findByName(name);
		
		if (!queryClass.isPresent()) {
			log.warn("Name given not associated with a class in database. Name passed was: \"{}\". Returning a 400 error", name);
			throw new SpellNotFoundException("Invalid class name. Name not in database");
		}
		
		List<Spell> spells = queryClass.get().getSpells();
		
		Iterator<Spell> spellIterator = spells.iterator();
		
		while (spellIterator.hasNext()) {
			Spell currentSpell = spellIterator.next();
			if (currentSpell.getLevel() != level) {
				spellIterator.remove();
			}
		}
		
		return spells;
	}
	
}
