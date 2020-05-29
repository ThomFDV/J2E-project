import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  public saveToken(token: string) {
    if (!token) return;
    window.localStorage.removeItem('AuthToken');
    window.localStorage.setItem('AuthToken',  token);
  }
}
