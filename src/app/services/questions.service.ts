import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  postQuestion(question: any): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(this.apiUrl + 'question/post', question, { headers });
  }

  getQuestions(): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.get(this.apiUrl + 'question/get', { headers });
  }

  getAllQuestions(): Observable<any> {
    return this.http.get(this.apiUrl + 'question/getall').pipe(
      tap((response: any) => {
        return response;
      })
    );
  }

  getQuestionById(id: string): Observable<any> {
    return this.http.get(this.apiUrl + 'question/get/' + id).pipe(
      tap((response: any) => {
        return response;
      })
    );
  }

  getQuestionByUserId(id: string): Observable<any> {
    return this.http.get(this.apiUrl + 'question/getuser/' + id).pipe(
      tap((response: any) => {
        return response;
      })
    );
  }

  postAnswer(answer: any): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(this.apiUrl + 'answer/add', answer, { headers });
  }

  getAnswers(id: string): Observable<any> {
    return this.http.get(this.apiUrl + 'answer/get/' + id).pipe(
      tap((response: any) => {
        return response;
      })
    );
  }

  getAnswersByUserId(id: string): Observable<any> {
    return this.http.get(this.apiUrl + 'answer/getuser/' + id).pipe(
      tap((response: any) => {
        return response;
      })
    );
  }

  getQuestionsByQuery(search: string): Observable<any> {
    return this.http.get(this.apiUrl + 'question/search/' + search).pipe(
      tap((response: any) => {
        return response;
      })
    );
  }

  voteQuestion(qid: string, direction: string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(this.apiUrl + 'question/vote/' + direction, { qid }, { headers });
  }

  voteAnswer(aid: string, direction: string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(this.apiUrl + 'answer/vote/' + direction, { aid }, { headers });
  }

  viewQuestion(qid: string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(this.apiUrl + 'question/view', { qid }, { headers });
  }

  acceptAnswer(aid: string, qid: string): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = { 'Authorization': `Bearer ${token}` };
    return this.http.post(this.apiUrl + 'answer/accept', { aid, qid }, { headers });
  }
}
