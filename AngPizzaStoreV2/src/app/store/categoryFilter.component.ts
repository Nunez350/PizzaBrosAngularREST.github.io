import { Component, OnInit } from '@angular/core';
import { Repository } from '../models/repository';

@Component({
    selector: 'store-categoryFilter',
    templateUrl: 'categoryFilter.component.html'
})
export class CategoryFilterComponent implements OnInit {

    categories: string[] =[];
    constructor(private repo: Repository) { }

    public ngOnInit(){
        // subscribe to the category -ra
        this.repo.categoryFetched.subscribe(res => {
            this.categories = res;
        });
    }
    
    get currentCategory(): string {
        return this.repo.filter.category;
    }
    setCurrentCategory(newCategory: string) {
        this.repo.filter.category = newCategory;
        this.repo.getProducts();
    }

}
