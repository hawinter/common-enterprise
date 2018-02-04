import { Component } from '@angular/core';
import {AuthService} from './auth.service'

@Component({
  selector: 'login-form',
  providers: [AuthService],  
  styleUrls: ['login.component.css'],
  templateUrl: 'login.component.html'
})
export class LoginComponent {
    public loginData = {username: "", password: ""};

    constructor(private authService:AuthService) {}
 
    login() {
        this.authService.obtainAccessToken(this.loginData);
    }
}
