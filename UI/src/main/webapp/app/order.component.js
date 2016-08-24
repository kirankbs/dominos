"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var router_1 = require('@angular/router');
var checkout_service_1 = require('./checkout.service');
var OrderComponent = (function () {
    function OrderComponent(checkoutService, route, router) {
        this.checkoutService = checkoutService;
        this.route = route;
        this.router = router;
    }
    OrderComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.route.params.forEach(function (params) {
            _this.totalPrice = parseFloat(params['total']);
            _this.checkoutService.getCheckoutPizzas().then(function (pizzas) { return _this.pizzas = pizzas; });
        });
    };
    OrderComponent = __decorate([
        core_1.Component({
            selector: 'pizza-order',
            templateUrl: 'app/order.component.html',
            styleUrls: ['app/order.component.css']
        }), 
        __metadata('design:paramtypes', [checkout_service_1.CheckoutService, router_1.ActivatedRoute, router_1.Router])
    ], OrderComponent);
    return OrderComponent;
}());
exports.OrderComponent = OrderComponent;
//# sourceMappingURL=order.component.js.map