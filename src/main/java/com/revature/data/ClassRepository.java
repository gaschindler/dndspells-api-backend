package com.revature.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {

	Optional<Class> findByName(String name);
	
}
