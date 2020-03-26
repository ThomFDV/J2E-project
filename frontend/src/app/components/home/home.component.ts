import { Component, OnInit } from '@angular/core';
import { GifService } from 'src/app/services/gif.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  result: any;

  constructor(private gifService: GifService) { }

  ngOnInit() {
    this.gifService.getTrendingGif().subscribe((res) => {
      this.result = res;
      alert(JSON.stringify(res));
    }, (err) => {
      alert(err);
    });
  }

}
