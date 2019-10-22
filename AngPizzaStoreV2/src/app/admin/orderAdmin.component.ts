import { Component } from '@angular/core';
import { Repository } from '../models/repository';
import { Order } from '../models/order.module';

@Component({
    templateUrl: 'orderAdmin.component.html'
})
export class OrderAdminComponent {
    constructor(private repo: Repository) { }
    get orders(): Order[] {
        return this.repo.orders;
    }
    markDelivered(order: Order) {
        this.repo.deliverOrder(order);
    }
}
