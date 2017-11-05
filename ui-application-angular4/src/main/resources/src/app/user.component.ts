import { Component } from '@angular/core';
import {AppService} from './app.service'
import {UserService, Role, User, CommonResponse} from './user.service'

@Component({
  selector: 'user-home',
  providers: [UserService],  
  template: `<div class="container">
  <table class="table table-striped table-hover ">
  <thead>
    <tr>
      <th>#</th>
      <th>Username</th>
      <th>Full name</th>
      <th>Phone</th>
      <th>Email</th>
      <th>Address</th>
      <th>Enabled</th>
      <th>Roles</th>
      <th>Create time</th>
      <th>Update time</th>
    </tr>
  </thead>
  <tbody>
    <tr *ngFor="let user of users; let i = index">
      <td>{{i}}</td>
      <td>{{user.userName}}</td>
      <td>{{user.fullName}}</td>
      <td>{{user.phone}}</td>
      <td>{{user.email}}</td>
      <td>{{user.address}}</td>
      <td>{{user.enabled}}</td>
      <td>
        <span *ngFor="let role of user.roles; let last = last">
          {{role.name}} <span *ngIf="!last">,</span>
        </span> 
      </td>
      <td>{{user.createTime}}</td>
      <td>{{user.updateTime}}</td>
    </tr>
  </tbody>
</table> 
</div>`
})

export class UserComponent {
    public response : CommonResponse;
    public users : Array<User>;
    private userListUrl = 'http://localhost:8082/common-resource/users';  

    constructor(private _AppService:AppService, private _UserService:UserService) {}

    ngOnInit(){
        this._AppService.checkCredentials();
        this._UserService.getListUsers(this.userListUrl)
        .subscribe(
                    data => {
                      this.response = data;
                      this.users=data.value;
                      console.log('response: ', this.response);
                      console.log('users: ', this.users); 
                    },
                    error =>  console.log('[UserComponent] Something went wrong!'));
    }
}
