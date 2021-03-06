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
    return this.http.get<Post[]>(this.backendUrl);
  }

  getPostById(postId: string): Observable<Post> {
    const authHeader = { headers: this.token.getHeaderToken() };
    return this.http.get<Post>(`${this.backendUrl}/${postId}`, authHeader);
  }

  addPost(post: Post, gifUrl): Observable<Post> {
    const authHeader = { headers: this.token.getHeaderToken() };
    return this.http.post<Post>(this.backendUrl, {
      title: post.title,
      content: post.content,
      gifUrl
    }, authHeader)
  }
}
