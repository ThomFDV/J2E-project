import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../../models/post";
import {Comment} from "../../../models/comment";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.scss']
})
export class CommentListComponent implements OnInit {

  @Input() post: Post;
  comments: Comment[];

  constructor() { }

  ngOnInit() {
    this.comments = this.post.comments;
  }

}
