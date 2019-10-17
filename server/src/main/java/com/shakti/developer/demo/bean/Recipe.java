package com.shakti.developer.demo.bean;


import lombok.*;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString @EqualsAndHashCode
public class Recipe {
    @Id @GeneratedValue
    private long id;
    private @NonNull String name;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")  
    private @NonNull LocalDateTime createdDateTime; 
    private boolean isVegeterian; 
    private int suitableFor;
    private @NonNull String cookingInstructions;
    @ElementCollection
    private List<String> ingredientList = new ArrayList<String>();
 
 
}
