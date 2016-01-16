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
    var self = this;

    self.save = function(cart)
    {
      cart = angular.toJson(cart);
      var storage = $window.localStorage;

      storage.setItem('cart', cart);
    };

    self.retrieve = function()
    {
      var cart = $window.localStorage.getItem("cart");

      cart = JSON.parse(cart);
      return cart;


    }
  });
