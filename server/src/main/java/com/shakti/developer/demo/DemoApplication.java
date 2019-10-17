package com.shakti.developer.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.shakti.developer.demo.bean.Recipe;
import com.shakti.developer.demo.service.RecipeServiceImpl;
import com.shakti.developer.demo.repository.RecipeRepository;
import java.util.List;


@SpringBootApplication
public class DemoApplication {
	
	@Autowired
	private RecipeServiceImpl service;

	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	

	 @Bean
	    ApplicationRunner init(RecipeRepository repository) {
		 return args -> {
			    List<Recipe> recipes=service.addDummyRecipesOnLoad();
				recipes.stream().forEach(recipe->repository.save(recipe));
	        };
	    }
	 


}
