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

  gifContainer: any;

  constructor(private gifService: GifService,
              public dialog: MatDialog) { }

  ngOnInit() {
    this.gifService.getTrendingGif().subscribe((res) => {
      this.gifContainer = res.data;
    }, (err) => {
      alert(err);
    });
  }

  openRandomGif() {
    this.dialog.open(RandomGifComponent);
  }

}
