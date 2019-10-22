import { NgModule } from '@angular/core';
import { Repository } from './repository';
import { Cart } from './cart.model';
import { Order } from './order.module';

@NgModule({
  providers: [Repository, Cart, Order]
})
export class ModelModule { }
