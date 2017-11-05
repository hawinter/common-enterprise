import {Component} from '@angular/core';
import {AppService} from './app.service'
 
@Component({
    selector: 'app-root',
    providers: [AppService],
    template: ` 
    <app-header></app-header>
    <router-outlet></router-outlet>
    <app-footer></app-footer>
    `
})

export class AppComponent {
  public isLoggedIn : boolean;
  constructor(private _service:AppService){}

  ngOnInit(){
    this.isLoggedIn = this._service.isLoggedIn();
  }

  logout() {
      this._service.logout();
  }
}