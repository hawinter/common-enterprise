import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminProductService } from './admin.product.service'
import { Product } from './Product'
import { CommonResponse } from '../common/CommonResponse';
import { NotificationService } from '../common/NotificationService';

@Component({
  selector: 'admin-product',
  providers: [AdminProductService, NotificationService],
  styleUrls: ['admin.product.component.css'],
  templateUrl: 'admin.product.component.html'
})

export class AdminProductComponent {
  public response: CommonResponse<Product>;
  public errorMessage: any;
  public product: Product;
  private productUrl = 'http://localhost:8082/common-resource/product';

  private router: Router;
  private adminProductService: AdminProductService;
  private notificationService: NotificationService;

  constructor(
    router: Router, 
    adminProductService: AdminProductService, 
    notificationService: NotificationService
  ) { 
    this.router = router;
    this.adminProductService = adminProductService;
    this.notificationService = notificationService;
  }

  categories = [
    { id: 1, name: "Men" }, { id: 2, name: "Women" },
    { id: 3, name: "Kid" }, { id: 4, name: "Shoes" },
    { id: 5, name: "Shirt" }, { id: 6, name: "Trousers" },
    { id: 7, name: "Other" }
  ];
  madeIns = ['China', 'Japan', 'Vietnam'];
  sizes = ['XS', 'S', 'L', 'XL', 'XXL'];

  created = false;

  onCreate() { this.created = true; };

  onSubmit() {
    this.adminProductService
      .createProduct(this.productUrl, this.product)
      .subscribe(
      result => {
        this.response = result;
        this.product = this.response.value;
        console.log(result);
        if (this.response.resultCode == 0) {
          console.log("Success");
          this.notificationService.success('Creation result', 'The product has been created successfully');
        } else {
          console.log("Fail");
          this.notificationService.error('Creation result', this.response.message);
        }

        this.gotoProductCreation();
      },
      error => {
        this.errorMessage = <any>error;
        console.log("Error");
        console.log(this.errorMessage);
        this.notificationService.error('Creation result', this.errorMessage);

        this.gotoProductCreation();
      }
      );
  };

  gotoProductCreation() {
    this.reset();
    this.created = false;
    this.router.navigate(["/admin-product"]);
  }

  // TODO: Remove this when we're done
  get diagnostic() { return JSON.stringify(this.product); }

  reset() {
    this.product = new Product(
      null,
      '',
      null,
      null,
      null,
      null,
      null,
      '',
      '',
      '',
      '',
      '',
      '',
      ''
    );
  }

  ngOnInit() {
    this.reset();
  }
}
