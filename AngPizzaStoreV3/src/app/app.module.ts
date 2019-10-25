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
import { LogInComponent } from './LogIn/LogIn.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AboutUsComponent } from './aboutUs/aboutus.component';
import { CustomerComponent } from './customer/customer.component';

@NgModule({
  declarations: [
<<<<<<< HEAD
    AppComponent, SignInComponent, HomeComponent, NavbarComponent, AboutUsComponent, CustomerComponent
=======
    AppComponent, LogInComponent, HomeComponent, NavbarComponent, AboutUsComponent
>>>>>>> ca10c8de28874fc5f12533a5ea86fa3eb89b378a
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
