import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../shared/recipe/recipe.service';
import { GiphyService } from '../shared/giphy/giphy.service';



@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})


export class RecipeListComponent implements OnInit {
  recipes: Array<any>;

  constructor(private recipeService: RecipeService,private giphyService: GiphyService) { }

  ngOnInit() {
    this.recipeService.getAll().subscribe(data => {
      this.recipes = data;
      for (const recipe of this.recipes) {
        this.giphyService.get(recipe.name).subscribe(url => recipe.giphyUrl = url);
      }
    });
  }

}




