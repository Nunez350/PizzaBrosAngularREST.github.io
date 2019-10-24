import { Component } from '@angular/core';
import { Repository } from '../models/repository';
import { Product } from '../models/product.model';

@Component({
  selector: 'admin-product-editor',
  templateUrl: 'productEditor.component.html'
})
export class ProductEditorComponent {
  constructor(private repo: Repository) { }
  showNavbar = false;
  get product(): Product {
    return this.repo.product;
  }
}
