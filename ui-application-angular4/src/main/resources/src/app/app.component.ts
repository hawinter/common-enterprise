import {Component} from '@angular/core';
import {AppService} from './app.service';
import {ToasterContainerComponent, ToasterService, ToasterConfig} from 'angular2-toaster';
 
@Component({
    selector: 'app-root',
    providers: [AppService],
    template: ` 
    <app-header></app-header>
    <toaster-container [toasterconfig]="toasterconfig">
    </toaster-container>
    <router-outlet></router-outlet>
    <app-footer></app-footer>
    `
})

export class AppComponent {
  public isLoggedIn : boolean;
  constructor(private _service:AppService){}

  public toasterconfig : ToasterConfig = 
  new ToasterConfig({
      positionClass: 'toast-bottom-right',    
      showCloseButton: true, 
      tapToDismiss: false, 
      timeout: 5000
  });

  ngOnInit(){
    this.isLoggedIn = this._service.isLoggedIn();
  }

  logout() {
      this._service.logout();
  }
}