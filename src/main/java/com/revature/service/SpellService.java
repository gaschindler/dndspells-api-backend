package com.revature.service;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.SpellRepository;
import com.revature.entities.Spell;
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
	
}
