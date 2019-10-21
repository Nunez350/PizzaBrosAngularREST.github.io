import { Injectable } from '@angular/core';
import { Cart } from './cart.model';
import { Repository } from './repository';
import { Router, NavigationStart } from '@angular/router';

export class Payment {
  cardNumber: string;
  cardExpiry: string;
  cardSecurityCode: string;
}

export class CartLine {
  constructor(private productId: number,
              private quantity: number) { }
}

export class OrderConfirmation {
  constructor(public orderId: number,
              public authCode: string,
              public amount: number) { }
}

@Injectable()
export class Order {
  orderId: number;
  customerName: string;
  address: string;
  payment: Payment = new Payment();
  submitted = false;
  delivered = false;
  orderConfirmation: OrderConfirmation;

  constructor(private repo: Repository, public cart: Cart, router: Router) {
    router.events
      .subscribe(event => {
        if (router.url.startsWith('/checkout') && this.customerName != null && this.address != null) {
          repo.storeSessionData('checkout', {
            customerName: this.customerName,
            cardNumber: this.payment.cardNumber,
            cardExpiry: this.payment.cardExpiry,
            cardSecurityCode: this.payment.cardSecurityCode
          });
        }
      });
    repo.getSessionData('checkout').subscribe(data => {
        if (data != null) {
          this.customerName = data.customerName;
          this.address = data.address;
          this.payment.cardNumber = data.cardNumber;
          this.payment.cardExpiry = data.cardExpiry;
          this.payment.cardSecurityCode = data.cardSecurityCode;
        }
      });
  }

  get products(): CartLine[] {
    return this.cart.selections
      .map(p => new CartLine(p.productId, p.quantity));
  }

  clear() {
    this.customerName = null;
    this.address = null;
    this.payment = new Payment();
    this.cart.clear();
    this.submitted = false;
  }
  submit() {
    this.submitted = true;
    //this.repo.createOrder(this);
  }
}
