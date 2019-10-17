import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipeService } from './shared/recipe/recipe.service';
import { GiphyService } from './shared/giphy/giphy.service';
import { HttpClientModule } from '@angular/common/http';
import { RecipeListComponent } from './recipe-list/recipe-list.component';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { RecipeEditComponent } from './recipe-edit/recipe-edit.component';




const appRoutes: Routes = [
  { path: '', redirectTo: '/recipe-list', pathMatch: 'full' },
  {
    path: 'recipe-list',
    component: RecipeListComponent
  },
  {
    path: 'recipe-add',
    component: RecipeEditComponent
  },
  {
    path: 'recipe-edit/:id',
    component: RecipeEditComponent
  }
];





@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    RecipeEditComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [RecipeService,GiphyService],
  bootstrap: [AppComponent]
})
export class AppModule { }




