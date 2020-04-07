import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GifService {

  url = "https://api.giphy.com/v1/gifs";
  apiKey = "api_key=srIYtmKcmFU2aU1WVx5sgKsMXeART9f2";

  constructor(private http: HttpClient) { }

  getTrendingGif(limit: Number): Observable<any> {
    return this.http.get<any>(`${this.url}/trending?${this.apiKey}&limit=${limit}&rating=G`);
  }

  getRandomGif(): Observable<any> {
    return this.http.get<any>(`${this.url}/random?${this.apiKey}&tag=&rating=G`);
  }

  getSearchGif(querry: String, limit: Number): Observable<any> {
    return this.http.get<any>(`${this.url}/search?${this.apiKey}&q=${querry}&limit=${limit}&offset=0&rating=G&lang=en`);
  }
}
