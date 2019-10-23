import { Injectable } from '@angular/core';
import { Product } from './product.model';
import { Repository } from './repository';


export class ProductSelection {
  constructor(public cart: Cart,
              public productId?: number,
              public name?: string,
              public price?: number,
              private quantityValue?: number) { }
  get quantity() {
    return this.quantityValue;
  }
  set quantity(newQuantity: number) {
    this.quantityValue = newQuantity;
    this.cart.update();
  }
}

@Injectable()
export class Cart {
  selections: ProductSelection[] = [];
  itemCount = 0;
  totalPrice = 0;
  // ---------------------------------------------------
  constructor(private repo: Repository) {
    repo.getSessionData('cart').subscribe(cartData => {
      if (cartData != null && cartData.selections.length > 0) {
        cartData.selections.map(item => new ProductSelection(this, item.pizzaId,
          item.name, item.price, item.quantity))
          .forEach(item => this.selections.push(item));
        this.update(false);
      }
    });
  }

    addProduct(product: Product) {
    const selection = this.selections.find(ps => ps.productId === product.productId);
    if (selection) {
      selection.quantity++;
    } else {
      this.selections.push(new ProductSelection(this,
        product.productId, product.name,
        product.price, 1));
    }
    this.update();
  }
  // ---------------------------------------------------
  updateQuantity(productId: number, quantity: number) {
    if (quantity > 0) {
      const selection = this.selections.find(ps => ps.productId === productId);
      if (selection) {
        selection.quantity = quantity;
      }
    } else {
      const index = this.selections.findIndex(ps => ps.productId === productId);
      if (index !== -1) {
        this.selections.splice(index, 1);
      }
      this.update();
    }
  }
  // ---------------------------------------------------
  clear() {
    this.selections = [];
    this.update();
  }
  // ---------------------------------------------------
  update(storeData: boolean = true) {
    this.itemCount = this.selections.map(ps => ps.quantity)
      .reduce((prev, curr) => prev + curr, 0);
    this.totalPrice = this.selections.map(ps => ps.price * ps.quantity)
      .reduce((prev, curr) => prev + curr, 0);

    // Added for session handling
    if (storeData) {
      this.repo.storeSessionData('cart', this.selections.map(s => {
        return {
          productId: s.productId,
          name: s.name,
          price: s.price,
          quantity: s.quantity
        };
      }));
    }
  }

}
