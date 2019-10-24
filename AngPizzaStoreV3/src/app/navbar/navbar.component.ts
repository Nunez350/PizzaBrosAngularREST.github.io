import { Component } from '@angular/core';
import { CartSummaryComponent } from '../store/cartSummary.component';


@Component({
    selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})

export class NavbarComponent {

  cart: CartSummaryComponent;

}
