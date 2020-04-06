import { Component, OnInit } from '@angular/core';
import { GifService } from 'src/app/services/gif.service';
import { MatDialog } from '@angular/material/dialog';
import { RandomGifComponent } from './random-gif/random-gif.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  gifContainer: any[] = [];
  gifLength: number = 0;

  constructor(private gifService: GifService,
              public dialog: MatDialog) { }

  ngOnInit() {
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
      this.gifContainer = this.gifContainer.concat(res.data);
    }, (err) => {
      console.log(err);
    })
  }

}
