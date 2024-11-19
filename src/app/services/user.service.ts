import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError, BehaviorSubject, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/';

  private profileUpdate = new BehaviorSubject<string>('');
  profileUpdate$ = this.profileUpdate.asObservable();

  constructor(private http: HttpClient) { }

  getUser(): Observable<any> {
    const token = localStorage.getItem('token');
    if(!token) {
      return throwError('No token found');
    }
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.get(this.apiUrl + 'user', { headers }).pipe(
      tap((response: any) => {
        localStorage.setItem('userId', response.id);
        return response;
      }),
      catchError((error) => {
        localStorage.clear();
        return throwError(error);
      })
    );
  }

  getUserById(id: string): Observable<any> {
    return this.http.get(this.apiUrl + 'user/' + id).pipe(
      tap((response) => { return response; }),
      catchError((error) => { return throwError(error); })
    );
  }

  updateUser(user: any): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(this.apiUrl + 'user/update', user, { headers }).pipe(
      tap((response: any) => {
        this.profileUpdate.next(response.image);
        return response; 
      }),
      catchError((error) => { return throwError(error); })
    );
  }

  updateProfileImage(): void {
    this.getUser().subscribe((response) => {
      this.profileUpdate.next(response.image);
    });
  }

  getAllUsers(): Observable<any> {
    return this.http.get(this.apiUrl + 'user/getall').pipe(
      tap((response) => {
        return response;
      }),
      catchError((error) => { return throwError(error); })
    );
  }

  getUserId(): string {
    return localStorage.getItem('userId') || '';
  }
}
