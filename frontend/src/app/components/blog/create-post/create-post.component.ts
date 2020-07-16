import { Component, OnInit } from '@angular/core';
import {PostService} from "../../../services/post.service";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {Post} from "../../../models/post";
import {GifService} from "../../../services/gif.service";

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

  postForm: FormGroup;

  constructor(private postService: PostService,
              private fb: FormBuilder,
              private router: Router,
              private gifService: GifService) { }

  ngOnInit() {
    this.postForm = this.fb.group({
      title: [],
      content: [],
      gif: []
    })
  }

  sendPost() {
    this.gifService.getSearchGif(this.postForm.get('gif').value, 1).subscribe((res) => {
      this.postService.addPost(this.postForm.value, res.data[0].embed_url)
        .subscribe((post: Post) => {
          this.router.navigateByUrl(`/blog/${post.id}`);
        })
    }, (err) => {
      console.log(err);
    });
  }

}
