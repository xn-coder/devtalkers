import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getTags(): Observable<any> {
    return this.http.get(this.apiUrl + 'tag/get').pipe(
      tap((response: any) => {
        return response;
      }),
      catchError((error) => {
        return throwError(error);
      })
    );
  }

  getTagNames(): Observable<any> {
    return this.http.get(this.apiUrl + 'tag/getname').pipe(
      tap((response: any) => {
        return response;
      }),
      catchError((error) => {
        return throwError(error);
      })
    );
  }

  addTag(tag: any): Observable<any> {
    return this.http.post(this.apiUrl + 'tag/add', tag).pipe(
      tap((response: any) => {
        return response;
      }),
      catchError((error) => {
        return throwError(error);
      })
    );
  }
}
