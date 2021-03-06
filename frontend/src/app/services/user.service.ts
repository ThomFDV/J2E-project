import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private backendUrl = 'http://localhost:8082';

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  register(user: User) {
    const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };
    console.log(`\nreceived:\n${JSON.stringify(user)}`);
    return this.http.post<User>(`${this.backendUrl}/auth/register`, user, httpOptions);
  }

  login(username: string, password: string): Observable<any> {
    return Observable.create(observer => {
      return this.http.post(`${this.backendUrl}/auth/login`, { username, password })
        .subscribe((data: any) => {
          observer.next(data);
          this.token.saveToken(data.accessToken);
          observer.complete();
        })
    });
  }

  isLoggedIn(): boolean {
    return this.token.getToken() !== null;
  }

  disconnect() {
    this.token.signOut();
  }

  getProfile() {
    const authHeader = { headers: this.token.getHeaderToken() };
    return this.http.get(`${this.backendUrl}/user/profile`, authHeader);
  }

  updateProfile(user: User, userId: Number) {
    const httpOptions = {
      headers: new HttpHeaders({
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.token.getToken()
        })
    };
    return this.http.put(`${this.backendUrl}/user/${userId}`, user, httpOptions);
  }
}
