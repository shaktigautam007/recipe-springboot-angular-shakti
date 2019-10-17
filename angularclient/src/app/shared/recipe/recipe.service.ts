import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';




@Injectable()
export class RecipeService {

  public API = '//localhost:8080';
  public RECIPE_API = this.API + '/recipes';

   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa('John:Doe')
    })
  };


  constructor(private http: HttpClient) {
     }

  
  getAll(): Observable<any> {
    return this.http.get('//localhost:8080/api/recipes',this.httpOptions);
  }

  
  get(id: string) {
    return this.http.get(this.RECIPE_API + '/' + id,this.httpOptions);
  }

  save(car: any): Observable<any> {
    let result: Observable<Object>;
    if (car['href']) {
      result = this.http.put(car.href, car);
    } else {
      result = this.http.post(this.RECIPE_API, car);
    }
    return result;
  }

  remove(href: string) {
    return this.http.delete(href);
  }


}

