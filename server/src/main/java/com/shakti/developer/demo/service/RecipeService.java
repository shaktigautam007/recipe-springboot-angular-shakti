package com.shakti.developer.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.shakti.developer.demo.bean.Recipe;


@Service
@CrossOrigin(origins = "http://localhost:4200")
public interface RecipeService {
	
	public List<Recipe> getAllRecipes();
	
	public Optional<Recipe> getRecipe(long recipeId) ;
		
	public void removeRecipe(long id);
	
	public Recipe addRecipe(Recipe recipe);
	
}
