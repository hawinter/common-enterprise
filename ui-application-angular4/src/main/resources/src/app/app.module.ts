import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header.component';
import { FooterComponent } from './footer.component';
import { LoginComponent } from './login.component';
import { HomeComponent } from './home.component';
import { FooComponent } from './foo.component';
import { UserComponent } from './user.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    FooComponent,
    UserComponent    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
     { path: '', redirectTo: '/home', pathMatch: 'full' },
     { path: 'home', component: HomeComponent },
     { path: 'login', component: LoginComponent },
     { path: 'user', component: UserComponent }])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
