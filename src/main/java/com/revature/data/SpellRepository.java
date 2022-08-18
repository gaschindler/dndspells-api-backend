package com.revature.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Spell;

@Repository
public interface SpellRepository extends JpaRepository<Spell, Integer> {

}
