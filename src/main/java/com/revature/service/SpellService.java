package com.revature.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.data.SpellRepository;

@Service
public class SpellService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	private SpellRepository spellRepo;
	
	@Autowired
	public SpellService(SpellRepository spellRepo) {
		super();
		this.spellRepo = spellRepo;
	}
	
}
