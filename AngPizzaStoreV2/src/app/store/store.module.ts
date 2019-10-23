import { NgModule } from '@angular/core';
import { CartSummaryComponent } from './cartSummary.component';
import { PaginationComponent } from './pagination.componet';
import { ProductListComponent } from './productList.component';
import { ProductSelectionComponent } from './productSelection.component';
import { CartDetailComponent } from './cartDetail.component';
import { CheckoutDetailsComponent } from './checkout/checkoutDetails.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { OrderConfirmationComponent } from './checkout/orderConfirmation.component';
import { CheckoutSummaryComponent } from './checkout/checkoutSummary.component';
import { CheckoutPaymentComponent } from './checkout/checkoutPayment.component';
import { CategoryFilterComponent } from './categoryFilter.component';

@NgModule({
    declarations: [CartSummaryComponent, CategoryFilterComponent,
        PaginationComponent, ProductListComponent, ProductSelectionComponent,
        CartDetailComponent, CheckoutDetailsComponent, CheckoutPaymentComponent,
        CheckoutSummaryComponent, OrderConfirmationComponent],
    imports: [BrowserModule, RouterModule, FormsModule],
    exports: [ProductSelectionComponent]
})
export class StoreModule { }
