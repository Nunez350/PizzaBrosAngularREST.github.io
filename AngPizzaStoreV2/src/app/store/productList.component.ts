import { Component, OnInit } from '@angular/core';
import { Repository } from '../models/repository';
import { Product } from '../models/product.model';
import { Cart } from '../models/cart.model';

@Component({
  selector: 'store-product-list',
  templateUrl: 'productList.component.html'
})

export class ProductListComponent implements OnInit {
  constructor(private repo: Repository, private cart: Cart) { }

  products: Product[] = [];

  ngOnInit() {
    console.log('init called');
    this.load(1);
    this.repo.subscribeToProductFetch().subscribe(resp => {
      this.load(1);
    });
    this.repo.subscribeToPageChange().subscribe(page => this.load(page));
  }

  load(page: number) {
    if (this.repo.products != null && this .repo.products.length > 0) {
      const pageIndex = ( this.repo.pagination.currentPage - 1)
        * this.repo.pagination.productsPerPage;
      this.products = this.repo.products.slice(pageIndex,
        pageIndex + this.repo.pagination.productsPerPage);
    }
  }

  addToCart(product: Product) {
    this.cart.addProduct(product);
  }

}
