import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchGifComponent } from './search-gif.component';

describe('SearchGifComponent', () => {
  let component: SearchGifComponent;
  let fixture: ComponentFixture<SearchGifComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchGifComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchGifComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
