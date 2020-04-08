import { Component, OnInit, Input } from '@angular/core';
import { GifService } from 'src/app/services/gif.service';

@Component({
  selector: 'app-trending-gif',
  templateUrl: './trending-gif.component.html',
  styleUrls: ['./trending-gif.component.scss']
})
export class TrendingGifComponent implements OnInit {

  gifContainer: any[] = [];
  gifLength: number = 0;
  displayEnd: boolean = false;

  constructor(private gifService: GifService) { }

  ngOnInit() {
    this.gifService.getTrendingGif(6).subscribe((res) => {
      this.gifContainer = res.data;
      this.gifLength += 6;
    }, (err) => {
      console.log(err);
    });
  }

  onScroll() {
    if (this.gifLength >= 36) {
      this.displayEnd = true;
      return;
    }

    this.gifLength += 6;
    this.gifService.getTrendingGif(this.gifLength).subscribe((res) => {
      let data = res.data;
      data.splice(0, this.gifLength - 6);
      this.gifContainer = this.gifContainer.concat(data);
    }, (err) => {
      console.log(err);
    })
  }

}
