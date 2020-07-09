import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";
import {Post} from "../models/post";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private backendUrl = 'http://localhost:8082/blog/post';

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  getAllPost(): Observable<Post[]> {
    const authHeader = { headers: this.token.getHeaderToken() };
    return this.http.get<Post[]>(this.backendUrl, authHeader);
  }

  getPostById(postId: string): Observable<Post> {
    const authHeader = { headers: this.token.getHeaderToken() };
    return this.http.get<Post>(`${this.backendUrl}/${postId}`, authHeader);
  }

  addPost(title: string, content: string): Observable<Post> {
    const authHeader = { headers: this.token.getHeaderToken() };
    return this.http.post<Post>(this.backendUrl, {
      title,
      content
    }, authHeader)
  }
}
