import { Injectable } from '@angular/core';
<<<<<<< HEAD
import { HttpHeaders } from '@angular/common/http';
=======
>>>>>>> 30130e9b92ed7e47ff4015ae32f40e521b56262a

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  saveToken(token: string) {
    if (!token) return;
    window.localStorage.removeItem('AuthToken');
    window.localStorage.setItem('AuthToken',  token);
  }

  signOut() {
    window.localStorage.removeItem('AuthToken');
    window.localStorage.clear();
  }

  getToken(): string {
    return window.localStorage.getItem('AuthToken');
  }

  getHeaderToken(): HttpHeaders {
    const httpOptions = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.getToken()
    });
    return httpOptions;
  }
}
