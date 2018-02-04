import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from './order.service'
import { Order } from './Order'
import { CommonResponse } from '../common/CommonResponse';
import { NotificationService } from '../common/NotificationService';

@Component({
  selector: 'place-order',
  providers: [OrderService, NotificationService],
  styleUrls: ['order.component.css'],
  templateUrl: 'order.component.html'
})

export class AdminProductComponent {
  public response: CommonResponse<Order>;
  public errorMessage: any;
  public order: Order;
  private productUrl = 'http://localhost:8082/common-resource/order';

  private router: Router;
  private orderService: OrderService;
  private notificationService: NotificationService;

  constructor(
    router: Router, 
    orderService: OrderService, 
    notificationService: NotificationService
  ) { 
    this.router = router;
    this.orderService = orderService;
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
    this.orderService
      .placeOrder(this.productUrl, this.order)
      .subscribe(
      result => {
        this.response = result;
        this.order = this.response.value;
        console.log(result);
        if (this.response.resultCode == 0) {
          console.log("Success");
          this.notificationService.success('Creation result', 'The product has been created successfully');
        } else {
          console.log("Fail");
          //this.notificationsService.addWarning(this.response.message);
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
  get diagnostic() { return JSON.stringify(this.order); }

  reset() {
    this.order = new Order(
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
