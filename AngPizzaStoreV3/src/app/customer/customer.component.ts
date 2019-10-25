import { Component, OnInit } from '@angular/core';
import { Customer } from '../models/customer.model';
import { Repository } from '../models/repository';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent {
  constructor(private repo: Repository) { }
  showNavbar = false;
  get customer(): Customer {
    return this.repo.customer;
  }

}
