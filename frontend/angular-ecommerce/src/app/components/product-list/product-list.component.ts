import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/common/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list-grid.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product[];
  currentCategoryId: number;
  currentTitle: string;
  searchMode: boolean;
  
  constructor(private productService: ProductService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts();
    });
  }

  listProducts() {
    this.searchMode = this.route.snapshot.paramMap.has('keyword');

    if(this.searchMode) {
      this.currentTitle = `Search: ${this.route.snapshot.paramMap.get('keyword')}`;
      this.handleSearchProducts();
    } else {
      this.handleListProducts();
    }
  }

  handleSearchProducts() {
    const keyword: string = this.route.snapshot.paramMap.get('keyword');

    this.productService.searchProducts(keyword).subscribe(
      data => {
        this.products = data;
      }
    )
  }

  handleListProducts() {
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');

    if (hasCategoryId) {
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id');
      this.currentTitle = `Category: ${this.route.snapshot.paramMap.get('name')}`;
    } else {
      this.currentCategoryId = 1;
      this.currentTitle = 'Category: Books';
    }
    
    this.productService.getProductList(this.currentCategoryId).subscribe(
      data => {
        this.products = data;
      }
    )
  }
}
