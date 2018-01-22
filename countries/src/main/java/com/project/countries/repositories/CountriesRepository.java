package com.project.countries.repositories;

import com.project.countries.models.Countries;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository 												
public interface CountriesRepository extends CrudRepository<Countries,Long>{
	// Query methods go here.
	List<Countries> findAll();

	@Query("SELECT d FROM Countries d WHERE langauge = Slovene" )
	List<Countries> findAllDojos();
	// Example:
	// public YourModelHere findByName(String name);
}
