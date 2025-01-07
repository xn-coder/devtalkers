import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AichatService {

  private apiUrl = environment.apiUrl + 'chat/message';

  constructor(private http: HttpClient) { }

  sendMessage(message: string): Observable<any> {
    
    return this.http.post(this.apiUrl, message);
  }
}
