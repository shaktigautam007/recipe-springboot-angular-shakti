package com.shakti.developer.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.shakti.developer.demo.bean.Recipe;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	

}
