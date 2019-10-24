import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartSummaryComponent } from '../store/cartSummary.component';



@NgModule({
  declarations: [
    CartSummaryComponent
  ],
  imports: [
    CommonModule,
    CartSummaryComponent
  ]
})
export class NavbarModule { }
