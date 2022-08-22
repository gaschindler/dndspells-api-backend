package com.revature.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.SpellRepository;
import com.revature.entities.Spell;
import com.revature.exceptions.SpellNotFoundException;
import com.revature.smallentities.SmallSpell;

@Service
public class SpellService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private SpellRepository spellRepo;
	
	@Autowired
	public SpellService(SpellRepository spellRepo) {
		super();
		this.spellRepo = spellRepo;
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
	
	
	
}
