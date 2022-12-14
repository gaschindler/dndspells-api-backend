package com.revature.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.entities.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {

	Optional<School> findByName(String name);
	
}
