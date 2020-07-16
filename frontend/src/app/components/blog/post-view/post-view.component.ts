import { Component, OnInit } from '@angular/core';
import {PostService} from "../../../services/post.service";
import {ActivatedRoute} from "@angular/router";
import {Post} from "../../../models/post";

@Component({
  selector: 'app-post-view',
  templateUrl: './post-view.component.html',
  styleUrls: ['./post-view.component.scss']
})
export class PostViewComponent implements OnInit {

  post: Post;
  showCommentForm: boolean = false;

  constructor(private postService: PostService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getPost();
  }

  getPost() {
    this.postService.getPostById(this.route.snapshot.paramMap.get('postId')).subscribe((post: Post) => {
      this.post = post;
    });
  }

}
