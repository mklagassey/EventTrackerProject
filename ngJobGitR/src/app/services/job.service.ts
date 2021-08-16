import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Job } from '../models/job';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  baseUrl = environment.baseUrl;
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

    //   /**
//    * update
//  */
  public update(job: Job) {
    return this.http.put<Job>(this.url, job, this.getHttpOptions())
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError("YOU BROKE SOMETHING IN update()");
      })
    );
  }

    /**
   * show
   */
     public show(id: string) {
      return this.http.get<Job>(this.url + "/" + id, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError("YOU BROKE SOMETHING IN show()");
        })
      )
    }

}
