import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RandomGifComponent } from './random-gif/random-gif.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  isSearch: boolean = false;

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  openRandomGif() {
    this.dialog.open(RandomGifComponent);
  }

  receiveSearch($event) {
    this.isSearch = $event;
  }

}
