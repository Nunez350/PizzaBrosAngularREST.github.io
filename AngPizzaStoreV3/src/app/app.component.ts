import { Component } from '@angular/core';
import { Repository } from './models/repository';
import { Product } from './models/product.model';
import { Customer } from './models/customer.model';
import { Router, NavigationStart } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Brothers Pizza';
  showNavbar: boolean;
  constructor(private repo: Repository, router: Router) {
    router.events.forEach((event) => {
      if (router.url === '/admin' || router.url === '/admin/products' || router.url === '/admin/orders') {
        this.showNavbar = false;
      } else {
        this.showNavbar = true;
      }
    });
  }

  get product(): Product {
    return this.repo.product;
  }

  get products(): Product[] {
    return this.repo.products;
  }
}
