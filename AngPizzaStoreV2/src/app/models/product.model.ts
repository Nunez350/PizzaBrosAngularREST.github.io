export class Product {
  constructor(
    public productId?: number,
    public name?: string,
    public price?: number,
    public category?: string,
    public description?: string,
    public image?: string,
    public inventory?: number,
    public productSales?: number) { }
}
