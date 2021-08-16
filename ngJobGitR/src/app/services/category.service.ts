import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  baseUrl = 'http://localhost:8084/';
  url = this.baseUrl + 'api/categories';

  constructor(
    private http: HttpClient
  ) { }

  getHttpOptions() {
    // const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders( {
        'Content-type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
        // 'Authorization': `Basic ${credentials}`
      }),
    }
    return httpOptions;
  }

  index(): Observable<Category[]> {
    return this.http.get<Category[]>(this.url).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("YOU BROKE SOMETHING IN index()");
        })
      )
  }
}
