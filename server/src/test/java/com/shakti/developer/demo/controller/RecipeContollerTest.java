package com.shakti.developer.demo.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shakti.developer.demo.bean.Recipe;
import com.shakti.developer.demo.service.RecipeService;

 
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class RecipeContollerTest
{
    @InjectMocks
    RecipeController recipeContoller;
     
    @Mock
    RecipeService recipeService;
     
 
     
    @Test
    public void testFindAll()
    {
        // given
    	Recipe pizza=new Recipe(1, "Pizza", LocalDateTime.now(), false, 3, "Pizza Cooking Instructions", Arrays.asList("Pizza Ing 1","Pizza Ing 2"));
		Recipe pasta=new Recipe(2, "Pasta", LocalDateTime.now(), false, 4, "Pasta Cooking Instructions", Arrays.asList("Pasta Ing 1","Pasta Ing 2"));
		
		List<Recipe> recipes=new ArrayList<>();

		recipes.add(pizza);
		recipes.add(pasta);
       
 
        when(recipeService.getAllRecipes()).thenReturn(recipes);
 
        // when
        List<Recipe> result = recipeContoller.getAllRecipes();
 
        // then
        assertThat(result.size()).isEqualTo(2);
         
        assertThat(result.get(0).getName())
                        .isEqualTo(pizza.getName());
         
        assertThat(result.get(1).getName())
                        .isEqualTo(pasta.getName());
    }
    
    
    @Test
    public void testFindById()
    {
        // given
    	Recipe pizza=new Recipe(1, "Pizza", LocalDateTime.now(), false, 3, "Pizza Cooking Instructions", Arrays.asList("Pizza Ing 1","Pizza Ing 2"));
    	Optional<Recipe> recipeOptional=Optional.of(pizza);
 
        when(recipeService.getRecipe(1)).thenReturn(recipeOptional);
 
        // when
        Recipe  recipeFound = recipeContoller.getRecipe(1);
 
        // then
        assertThat(recipeFound.getName())
                        .isEqualTo(pizza.getName());
         
     
    }
    
    
    
    
    @Test
    public void testAddRecipe()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        //Given
    	Recipe pizza=new Recipe(1, "Pizza", LocalDateTime.now(), false, 3, "Pizza Cooking Instructions", Arrays.asList("Pizza Ing 1","Pizza Ing 2"));

         //When
        when(recipeService.addRecipe(any(Recipe.class))).thenReturn(pizza);
         
        ResponseEntity<Object> responseEntity= recipeContoller.addRecipe(pizza);
        //then
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }
    
   

    
}