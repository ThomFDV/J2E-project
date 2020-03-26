import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'safe'
})
export class SafePipe implements PipeTransform {
  constructor(private sanitzer: DomSanitizer) { }

  transform(url) {
    return this.sanitzer.bypassSecurityTrustResourceUrl(url);
  }

}
