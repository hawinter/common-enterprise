import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
 
export class Role {
  constructor(
    public id: number,
    public name: string,
    public desc: number
  ) {}
}
export class User {
  constructor(
    public id: number,
    public username: string,
    public password: number,
    public fullName: string,
    public phone: string,
    public email: string,
    public address: string,
    public enabled: number,
    private roles: Array<Role>
  ) { }
} 

export class CommonResponse {
  constructor (
    public resultCode: number,
    public message: string,
    public value: Array<User>
  ) {}
}

@Injectable()
export class UserService {
  constructor(
    private _router: Router, private _http: Http){}
 
  getListUsers(userListUrl) : Observable<CommonResponse> {
    var headers = new Headers({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Bearer '+Cookie.get('access_token')});
    var options = new RequestOptions({ headers: headers });
    return this._http.get(userListUrl, options)
                   .map((res:Response) => res.json())
                   .catch((error:any) => Observable.throw(error.json().error || '[UserService] Server error'));
  }
}