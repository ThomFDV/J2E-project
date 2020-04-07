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

  gifContainer: any[] = [];
  gifLength: number = 0;
  searchForm: FormGroup;

  constructor(private gifService: GifService,
              public dialog: MatDialog,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.searchForm = this.fb.group({
      searchField: []
    })
    this.gifService.getTrendingGif(6).subscribe((res) => {
      this.gifContainer = res.data;
      this.gifLength += 6;
    }, (err) => {
      console.log(err);
    });
  }

  openRandomGif() {
    this.dialog.open(RandomGifComponent);
  }

  onScroll() {
    if (this.gifLength >= 36) return;

    this.gifLength += 6;
    this.gifService.getTrendingGif(this.gifLength).subscribe((res) => {
      let data = res.data;
      data.splice(0, this.gifLength - 6);
      this.gifContainer = this.gifContainer.concat(data);
    }, (err) => {
      console.log(err);
    })
  }

  searchGif() {
    console.log(`searched ${this.searchForm.get('searchField').value}!`);
  }

}
