import { Component, OnInit } from '@angular/core';
import { GifService } from 'src/app/services/gif.service';
import { MatDialog } from '@angular/material/dialog';
import { RandomGifComponent } from './random-gif/random-gif.component';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  searchForm: FormGroup;

  constructor(private gifService: GifService,
              public dialog: MatDialog,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.searchForm = this.fb.group({
      searchField: []
    })
  }

  openRandomGif() {
    this.dialog.open(RandomGifComponent);
  }

  searchGif() {
    console.log(`searched ${this.searchForm.get('searchField').value}!`);
    this.gifService.getSearchGif(this.searchForm.get('searchField').value, 10).subscribe((res) => {
      console.log(res.data);
    }, (err) => {
      console.log(err);
    });
  }

  /*
  TODO;
    - Split the Search and Trends in two components
    - Put the title in a var to update the text
    - Update the fonction to display more gif to be the same as the two components
  */

}
