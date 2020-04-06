import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GifService {

  url = "https://api.giphy.com/v1/gifs"

  constructor(private http: HttpClient) { }

  getTrendingGif(limit): Observable<any> {
    return this.http.get<any>(`${this.url}/trending?api_key=srIYtmKcmFU2aU1WVx5sgKsMXeART9f2&limit=${limit}&rating=G`);
  }

  getRandomGif(): Observable<any> {
    return this.http.get<any>(`${this.url}/random?api_key=srIYtmKcmFU2aU1WVx5sgKsMXeART9f2&tag=&rating=G`);
  }
}
