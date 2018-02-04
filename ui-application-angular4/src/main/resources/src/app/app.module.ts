import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';
import {NoopAnimationsModule} from '@angular/platform-browser/animations';
import {ToasterModule, ToasterService} from 'angular2-toaster';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
import { NotificationComponent } from './common/Notification.component';
import { AuthService } from './authentication/auth.service';
import { EnsureAuthenticated } from './authentication/ensure-authenticated.service';
import { LoginRedirect } from './authentication/login-redirect.service';
import { LoginComponent } from './authentication/login.component';
import { HomeComponent } from './home.component';
import { UserComponent } from './user.component';
import { AdminProductComponent } from './product.components/admin.product.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NotificationComponent,
    HomeComponent,
    LoginComponent,
    UserComponent,
    AdminProductComponent    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    NoopAnimationsModule,
    ToasterModule,
    RouterModule.forRoot([
     { path: '', redirectTo: '/home', pathMatch: 'full' },
     { path: 'home', component: HomeComponent },
     { path: 'login', component: LoginComponent, canActivate: [LoginRedirect] },
     { path: 'user', component: UserComponent, canActivate: [EnsureAuthenticated] },
     { path: 'admin-product', component: AdminProductComponent, canActivate: [EnsureAuthenticated] }])
  ],
  providers: [
    AuthService,
    EnsureAuthenticated,
    LoginRedirect
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
