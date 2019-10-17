import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipeService } from '../shared/recipe/recipe.service';
import { GiphyService } from '../shared/giphy/giphy.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-recipe-edit',
  templateUrl: './recipe-edit.component.html',
  styleUrls: ['./recipe-edit.component.css']
})
export class RecipeEditComponent implements OnInit, OnDestroy {
  recipe: any = {};

  sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private recipeService: RecipeService,
              private giphyService: GiphyService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.recipeService.get(id).subscribe((recipe: any) => {
          if (recipe) {
            this.recipe = recipe;
            this.recipe.href = recipe._links.self.href;
            this.giphyService.get(recipe.name).subscribe(url => recipe.giphyUrl = url);
          } else {
            console.log(`Recipe with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/recipe-list']);
  }

  save(form: NgForm) {
    this.recipeService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  remove(href) {
    this.recipeService.remove(href).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }
}