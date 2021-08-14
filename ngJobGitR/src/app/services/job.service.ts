import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Job } from '../models/job';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  baseUrl = 'http://localhost:8084/';
  url = this.baseUrl + 'api/jobs';

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


  index(): Observable<Job[]> {
    return this.http.get<Job[]>(this.url).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("YOU BROKE SOMETHING IN index()");
        })
      )
  }

    /**
   * create
   */
     public create(job: Job) {
      // todo.completed = false;
      // todo.description = '';

      return this.http.post<Job>(this.url, job, this.getHttpOptions())
        .pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError("YOU BROKE SOMETHING IN create()");
          })
        );
    }

}
