import { Component, OnInit } from '@angular/core';
import {PostService} from "../../../services/post.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {Post} from "../../../models/post";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

  postForm: FormGroup;

  constructor(private postService: PostService,
              private fb: FormBuilder,
              private router: Router) { }

  ngOnInit() {
    this.postForm = this.fb.group({
      title: [],
      content: []
    })
  }

  sendPost() {
    this.postService.addPost(this.postForm.get('title').value, this.postForm.get('content').value)
      .subscribe((post: Post) => {
        this.router.navigateByUrl(`/blog/${post.id}`);
      })
  }

}
