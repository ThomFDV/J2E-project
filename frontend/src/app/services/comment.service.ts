import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";
import {Comment} from "../models/comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private backendUrl = 'http://localhost:8082/blog/comment';

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  addComment(content: string, postId: string): Observable<Comment> {
    const authHeader = { headers: this.token.getHeaderToken() };
    return this.http.post<Comment>(`${this.backendUrl}/${postId}`, {
      content
    }, authHeader);
  }
}
