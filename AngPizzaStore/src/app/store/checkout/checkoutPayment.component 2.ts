import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Order } from 'src/app/models/order.module';

@Component({
  templateUrl: 'checkoutPayment.component.html',

})
export class CheckoutPaymentComponent{
  constructor(private router: Router, public order: Order){
    if (order.customerName == null || order.address == null){
      router.navigateByUrl('/checkout/step1');
    }
  }
}
