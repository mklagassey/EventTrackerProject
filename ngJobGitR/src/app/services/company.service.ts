import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Company } from '../models/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  baseUrl = environment.baseUrl;
  url = this.baseUrl + 'api/companies';

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

  index(): Observable<Company[]> {
    return this.http.get<Company[]>(this.url).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("YOU BROKE SOMETHING IN index()");
        })
      )
  }
}
