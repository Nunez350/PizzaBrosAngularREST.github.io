import { Component } from '@angular/core';
import { Customer } from '../models/customer.model';

@Component({
    templateUrl: './LogIn.component.html',
    styleUrls: ['./LogIn.css']
})

export class LogInComponent{

  customer: Customer;

  checkLogin() {

  }
}
