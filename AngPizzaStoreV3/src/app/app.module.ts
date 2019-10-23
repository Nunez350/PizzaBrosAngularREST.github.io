import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { RoutingConfig } from './app.routing';
import { HttpClientModule } from '@angular/common/http';
import { ModelModule } from './models/model.module';
import { StoreModule } from './store/store.module';
import { AdminModule } from './admin/admin.module';
import { ProductDetailComponent } from './structure/productDetail.component';
import { ProductTableComponent } from './structure/productTable.component';
import { CategoryFilterComponent } from './structure/categoryFilter.component';
import { HomeComponent } from './home/home.component';

@NgModule({
  declarations: [
    AppComponent, ProductDetailComponent, ProductTableComponent, CategoryFilterComponent, HomeComponent
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
