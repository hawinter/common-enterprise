import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { Product } from './Product';
import { CommonResponse } from '../common/CommonResponse';

@Injectable()
export class AdminProductService {
  public response: CommonResponse<Product>;

  headers: Headers;
  options: RequestOptions;

  constructor(private http: Http) {
    this.headers = new Headers({
      'Content-Type': 'application/json; charset=utf-8'
    });
    this.options = new RequestOptions({ headers: this.headers });
  }

  createProduct(url: string, param: any): Observable<any> {
    let body = JSON.stringify(param);
    return this.http
      .post(url, body, this.options)
      .map(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    console.log("Create product server response: " + body);
    return body || {};
  }

  private handleError(error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
