package com.shakti.developer.demo.controller;

import com.shakti.developer.demo.Error.RecipeNotFoundException;
import com.shakti.developer.demo.bean.Recipe;
import com.shakti.developer.demo.service.RecipeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api")
class RecipeController {
	
	public static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
	
	@Autowired
	private RecipeService service;

	
	@GetMapping("/recipes")
	public List<Recipe> getAllRecipes() {
		logger.info("Fetching All Recipes");
		return service.getAllRecipes();
	}
	
	
	@GetMapping("/recipes/{id}")
	public Recipe getRecipe(@PathVariable long id) {
		logger.info("Fetching Recipe with id {}", id);
		Optional<Recipe> recipe = service.getRecipe(id);

		if (!recipe.isPresent()) {
			logger.error("Recipe with id {} not found.", id);
			throw new RecipeNotFoundException("id-" + id);
		}

		return recipe.get();
	}
	
	@DeleteMapping("/recipes/{id}")
	public void deleteRecipe(@PathVariable long id) {
        logger.info("Deleting Recipe with id {}", id);

		service.removeRecipe(id);
	}
	
	@PostMapping("/recipes")
	public ResponseEntity<Object> addRecipe(@RequestBody Recipe recipe) {
        logger.info("Creating Recipe : {}", recipe);

		Recipe savedRecipe = service.addRecipe(recipe);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedRecipe.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/recipes/{id}")
	public ResponseEntity<Object> updateRecipe(@RequestBody Recipe recipe, @PathVariable long id) {
		
        logger.info("Updating Recipe with id {}", id);


		Optional<Recipe> recipeOptional = service.getRecipe(id);

		if (!recipeOptional.isPresent())
			return ResponseEntity.notFound().build();

		recipe.setId(id);
		
		service.addRecipe(recipe);

		return ResponseEntity.noContent().build();
	}
	
	

}
