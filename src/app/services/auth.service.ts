import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError, tap, BehaviorSubject } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
    private apiUrl = environment.apiUrl;

    private authState = new BehaviorSubject<boolean>(false);
    authState$ = this.authState.asObservable();

    constructor(private http: HttpClient) {}

    registerUser(userData: any): Observable<any> {
        return this.http.post(this.apiUrl + 'signup', userData).pipe(
            tap((response) => {
                return response;
            }),
            catchError((error) => {
                return throwError(error);
            })
        );
    }

    loginUser(userData: any): Observable<any> {
        return this.http.post(this.apiUrl + 'login', userData).pipe(
            tap((response: any) => {
                localStorage.setItem('token', response.token);
                this.authState.next(true);
                return response;
            }),
            catchError((error) => {
                return throwError(error);
            })
        );
    }

    isAuthenticated() {
        return localStorage.getItem('token') !== null;
    }

    checkAuthState() {
        const isAuthenticated = localStorage.getItem('token') !== null;
        this.authState.next(isAuthenticated);
    }

    logout() {
        localStorage.removeItem('token');
        this.authState.next(false);
    }
}