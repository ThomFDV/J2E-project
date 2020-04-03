import { Component, OnInit } from '@angular/core';
import { GifService } from 'src/app/services/gif.service';

@Component({
  selector: 'app-random-gif',
  templateUrl: './random-gif.component.html',
  styleUrls: ['./random-gif.component.scss']
})
export class RandomGifComponent implements OnInit {

  gifContainer: any;

  constructor(private gifService: GifService) { }

  ngOnInit() {
    this.gifService.getRandomGif().subscribe((res) => {
      this.gifContainer = res.data;
    }, (err) => {
      console.log(err);
    })
  }

}
