import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private backendUrl = 'http://localhost:8082/user';

  constructor(private http: HttpClient) { }

  register(user: User) {
    const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };
    console.log(`\nreceived:\n${JSON.stringify(user)}`);
    return this.http.post<User>(this.backendUrl, user, httpOptions);
  }
}
