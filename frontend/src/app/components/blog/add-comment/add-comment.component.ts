import {Component, Input, OnInit} from '@angular/core';
import {CommentService} from "../../../services/comment.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Post} from "../../../models/post";
import {Comment} from "../../../models/comment";

@Component({
  selector: 'app-add-comment',
  templateUrl: './add-comment.component.html',
  styleUrls: ['./add-comment.component.scss']
})
export class AddCommentComponent implements OnInit {

  @Input() post: Post;
  commentForm: FormGroup;

  constructor(private commentService: CommentService, private fb: FormBuilder) { }

  ngOnInit() {
    this.commentForm = this.fb.group({
      content: []
    })
  }

  sendComment() {
    this.commentService.addComment(this.commentForm.get('content').value, this.post.id)
      .subscribe((comment: Comment) => {
        console.log(comment);
      })
  }

}
