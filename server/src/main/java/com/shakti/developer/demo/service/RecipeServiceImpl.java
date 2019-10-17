package com.shakti.developer.demo.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.shakti.developer.demo.bean.Recipe;
import com.shakti.developer.demo.repository.RecipeRepository;


@Service
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeServiceImpl implements RecipeService {
	@Autowired
	private RecipeRepository repository;
	
	
	@Override
	public List<Recipe> getAllRecipes() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Recipe> getRecipe(long recipeId) {
		return repository.findById(recipeId);
	}
	
	@Override
	public void removeRecipe(long id) {
		repository.deleteById(id);
		
	}
	
	@Override
	public Recipe addRecipe(Recipe recipe) {
		return repository.save(recipe);
	}
	
	/*
	 * Dummy method to insert initial records in h2 DB on application start-up so that
	 * user can see some recipes initially
	 */
	public List<Recipe> addDummyRecipesOnLoad(){
		
	
		Recipe pizza=new Recipe(1, "Pizza", LocalDateTime.now(), false, 3, "Pizza Cooking Instructions", Arrays.asList("Pizza Ing 1","Pizza Ing 2"));
		Recipe pasta=new Recipe(2, "Pasta", LocalDateTime.now(), false, 4, "Pasta Cooking Instructions", Arrays.asList("Pasta Ing 1","Pasta Ing 2"));
		
		List<Recipe> recipes=new ArrayList<>();

		recipes.add(pizza);
		recipes.add(pasta);
       
		return recipes;
				
	}

	


	


}

