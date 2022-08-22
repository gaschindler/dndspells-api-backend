package com.revature.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.revature.entities.Spell;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Integer> {

	@Query("select s.id, s.name from Spell s")
	List<Spell> findAll();
	
	Optional<Spell> findByName(String name);
	
	List<Spell> findByLevel(int level);
	
}
