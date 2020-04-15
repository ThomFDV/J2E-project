import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { GifService } from 'src/app/services/gif.service';

@Component({
  selector: 'app-search-gif',
  templateUrl: './search-gif.component.html',
  styleUrls: ['./search-gif.component.scss']
})
export class SearchGifComponent implements OnInit {

  searchForm: FormGroup;
  gifContainer: any[] = [];
  gifLength: number = 0;
  displayEnd: boolean = false;
  isSearch: boolean = false;
  @Output() searchEvent = new EventEmitter<boolean>();

  constructor(private gifService: GifService,
              private fb: FormBuilder) { }

  ngOnInit() {
    this.searchForm = this.fb.group({
      searchField: []
    })
  }

  searchGif() {
    this.gifLength += 6;
    this.gifService.getSearchGif(this.searchForm.get('searchField').value, this.gifLength).subscribe((res) => {
      this.gifContainer = res.data;
      this.isSearch = true;
      this.searchEvent.emit(true);
    }, (err) => {
      console.log(err);
    });
  }

  onScroll() {
    if (this.gifLength >= 24) {
      this.displayEnd = true;
      return;
    }

    this.gifLength += 6;
    this.gifService.getSearchGif(this.searchForm.get('searchField').value, this.gifLength).subscribe((res) => {
      let data = res.data;
      data.splice(0, this.gifLength - 6);
      this.gifContainer = this.gifContainer.concat(data);
    }, (err) => {
      console.log(err);
    })
  }

  reset() {
    this.isSearch = false;
    this.searchEvent.emit(false);
  }

}
