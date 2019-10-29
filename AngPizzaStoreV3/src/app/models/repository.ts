import { Product } from './product.model';
import { Order } from './order.module';
import { Customer } from './customer.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Filter, Pagination } from './configClasses.repository';
import { Subject } from 'rxjs';

const API_ENDPOINT = 'http://localhost:8080/api';
const productsUrl = API_ENDPOINT + '/products';
const ordersUrl = API_ENDPOINT + '/orders';
const sessionsUrl = API_ENDPOINT + '/session';
const customerUrl = API_ENDPOINT + '/customer';

@Injectable()
export class Repository {
  private filterObject = new Filter();
  private paginationObject = new Pagination();
  pageChange: Subject<number> = new Subject();
  productListFetched: Subject<boolean> = new Subject();
  // Added a subject to notify components -ra
  categoryFetched: Subject<string[]> = new Subject();


  product: Product;
  products: Product[];
  categories: string[] = [];
  orders: Order[] = [];
  customer: Customer;

  constructor(private http: HttpClient) {
    this.filter.related = true;
    this.getProducts();
  }

  subscribeToPageChange(): Subject<number> {
    return this.pageChange;
  }

  subscribeToProductFetch(): Subject<boolean> {
    return this.productListFetched;
  }

  changePage(page: number) {
    this.paginationObject.currentPage = page;
    this.pageChange.next(page);
  }

  getProduct(id: number) {
    this.http.get(productsUrl + '/' + id)
      .subscribe(response => this.product = response);
  }

  getCustomer(id: number) {
    this.http.get(customerUrl + '/' + id)
      .subscribe(response => this.customer = response);
  }

  getProducts(related = false) {
    let url = productsUrl + '?related=' + this.filterObject.related;
    if (this.filter.category) {
      url += '&category=' + this.filterObject.category;
    }
    if (this.filter.search) {
      url += '&search=' + this.filterObject.search;
    }

    url += '&metadata=true';
    this.http.get<any>(url).subscribe(response => {
      this.products = response;
      this.paginationObject.currentPage = 1;
      if (!this.filter.category) {
        this.populateCategories();
      }
      this.productListFetched.next(true);
    });
  }

  private populateCategories() {
    const groups = this.groupBy(this.products, 'category');
    this.categories = Object.keys(groups);
    this.categoryFetched.next(this.categories);
  }

  private groupBy = function (xs, key) {
    return xs.reduce(function (rv, x) {
      (rv[x[key]] = rv[x[key]] || []).push(x);
      return rv;
    }, {});
  };

  // subscribing to the category subject -ra
subscribeToCategories(): Subject<string[]>{
 return this.categoryFetched;
}


// Updated to be a post -ra
storeSessionData(dataType: string, data: any) {
  return this.http.post(sessionsUrl + '/' + dataType, data, { withCredentials: true })
    .subscribe(response => { });
}

  getSessionData(dataType: string): any {
    return this.http.get(sessionsUrl + '/' + dataType, { withCredentials: true});
  }

  createProduct(prod: Product) {
    this.http.post<Product>(productsUrl, prod, { withCredentials: true }).subscribe(response => {
      prod.productId = response.productId;
      this.products.push(prod);
    });
  }

  replaceProduct(prod: Product) {
    const data = {
      image: prod.image, name: prod.name, category: prod.category,
      description: prod.description, price: prod.price
    };
    this.http.put(productsUrl + '/' + prod.productId, data).subscribe(response => this.getProducts());
  }

  replaceCustomer(cust: Customer) {
    const data = {
      fname: cust.firstName, lname: cust.lastName, userName: cust.username,
      email: cust.email, address: cust.address, password: cust.password, points: cust.points
    };
    this.http.put(customerUrl + '/' + cust.customerId, data).subscribe(response => this.getCustomer(cust.customerId));
  }

  updateProduct(id: number, changes: Map<string, any>) {
    const patch = [];
    changes.forEach((value, key) =>
      patch.push({ op: 'replace', path: key, value }));
    this.http.patch(productsUrl + '/' + id, patch)
      .subscribe(response => this.getProducts());
  }

  updateCustomer(id: number, changes: Map<string, any>) {
    const patch = [];
    changes.forEach((value, key) =>
      patch.push({ op: 'replace', path: key, value }));
    this.http.patch(customerUrl + '/' + id, patch)
      .subscribe(response => this.getCustomer(id));
  }

  deleteProduct(id: number) {
    this.http.delete(productsUrl + '/' + id)
      .subscribe(response => this.getProducts());
  }

  deleteCustomer(id: number) {
    this.http.delete(customerUrl + '/' + id)
      .subscribe(response => this.getCustomer(id));
  }

  getOrders() {
    this.http.get<Order[]>(ordersUrl).subscribe(data => this.orders = data);
  }

  createOrder(order: Order) {
    this.http.post<any>(ordersUrl, {
      name: order.name,
      address: order.address,
      payment: order.payment,
      products: order.products    // Was movies previously -ra
    }, { withCredentials: true }).subscribe(data => {
      order.confirmation = data;
      order.cart.clear();
      order.clear();
    });
  }

  deliverOrder(order: Order) {
    this.http.post(ordersUrl + '/' + order.orderId, null, { withCredentials: true}).subscribe(r => this.getOrders());
  }

  get filter(): Filter {
    return this.filterObject;
  }

  get pagination(): Pagination {
    return this.paginationObject;
  }


}
