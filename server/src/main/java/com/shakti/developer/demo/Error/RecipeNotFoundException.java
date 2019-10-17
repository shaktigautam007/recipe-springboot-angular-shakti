package com.shakti.developer.demo.Error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException{


private static final long serialVersionUID = 1L;
private String id;

public RecipeNotFoundException(String id) {
super(String.format(" not found : '%s'",id));
this.id=id;

}

public String getId() {
return this.id;
}

}