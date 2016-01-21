'use strict';

/**
 * @ngdoc service
 * @name appApp.cartService
 * @description
 * # cartService
 * Service in the appApp.
 */
angular.module('appApp')
  .service('cartService', function ($window) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    this.cart = [];
    var self = this;

    self.init = function() {
      self.cart = self.retrieve() || [];
    };

    /**
     * TODO: This should not need a cart param.
     * @param cart
     */
    self.save = function(cart) {
      cart = angular.toJson(cart);
      var storage = $window.localStorage;

      storage.setItem('cart', cart);
    };

    self.retrieve = function() {
      var cart = $window.localStorage.getItem("cart");

      cart = JSON.parse(cart);
      return cart;
    };

    /**
     * TODO: Refactor this.
     * TODO: Dit zou veel simpelere moeten kunnen.
     * TODO: Param should be item, this makes it easier.
     * @param wijn
     */
    self.add = function (wijn) {
      var newWijn = true;
      self.cart.forEach(function(item) {
        if(angular.equals(item.wijn, wijn)) {
          newWijn = false;
          item.aantal++;
        }
      });
      if (newWijn) {
        var product = {"aantal": 1, "wijn": wijn};
        self.cart.push(product);
      }
      self.save(self.cart);
    };

    /**
     *
     * @param item
     */
    self.remove = function (index) {
      self.cart.splice(index, 1);
      self.save(self.cart);
    };

    self.calculateTotal = function(item) {
      return parseInt(item.aantal) * item.wijn.prijs;
    };

    self.getTotalAmount = function() {
      var total = 0;
      self.cart.forEach(function(item) {
        total += self.calculateTotal(item);
      });
      return Math.round(total * 100) / 100;
    };

    self.init();

  });
