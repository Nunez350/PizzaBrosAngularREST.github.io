import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { RoutingConfig } from './app.routing';
import { HttpClientModule } from '@angular/common/http';
import { ModelModule } from './models/model.module';
import { StoreModule } from './store/store.module';
import { AdminModule } from './admin/admin.module';
import { HomeComponent } from './home/home.component';
import { SignInComponent } from './SignIn/signIn.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AboutUsComponent } from './aboutUs/aboutus.component';
import { CustomerComponent } from './customer/customer.component';

@NgModule({
  declarations: [
    AppComponent, SignInComponent, HomeComponent, NavbarComponent, AboutUsComponent, CustomerComponent
  ],
  imports: [
    RoutingConfig,
    BrowserModule.withServerTransition({ appId: 'ng-cli-universal'}),
    HttpClientModule,
    ModelModule,
    FormsModule,
    StoreModule,
    AdminModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
