import { Component, OnInit } from '@angular/core';
import { Repository } from '../models/repository';
import { Product } from '../models/product.model';

@Component({
  selector: 'store-pagination',
  templateUrl: 'pagination.component.html'
})
export class PaginationComponent implements OnInit {
  constructor(private repo: Repository) { }
  pages: number[] = [];

  ngOnInit() {
    this.repo.subscribeToProductFetch().subscribe(resp => {
      this.pages = Array(Math.ceil(this.repo.products.length / this.repo.pagination.productsPerPage))
        .fill(0).map((x, i) => i + 1);
    });
  }

  get current(): number {
    return this.repo.pagination.currentPage;
  }

  changePage(newPage: number) {
    this.repo.changePage(newPage);
  }
}
