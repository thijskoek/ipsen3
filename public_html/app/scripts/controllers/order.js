'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:OrderCtrl
 * @description
 * # OrderCtrl
 * Controller of the appApp
 */

angular.module('appApp').controller('OrderCtrl', function ($scope, cartService, orderService, authenticationService, 
  $location, ngNotify) {
  $scope.cart = cartService.retrieve();
  $scope.user = authenticationService.getAuthenticator();

  $scope.getTotal = function(item) {
    return parseInt(item.aantal) * item.wijn.prijs;
  };

  $scope.getCartTotal = function() {
    var total = 0;

    $scope.cart.forEach(function(item) {
      total += $scope.getTotal(item);
    });
    return Math.round(total * 100) / 100
  };

  $scope.getBtwPrice = function() {
    var btw = 0;

    btw = ($scope.getCartTotal() / 121) * 21;
    return btw;
  };

  $scope.getSubtotaal = function() {
    var subtotaal = ($scope.getCartTotal() - $scope.getBtwPrice()) || 0;

    return subtotaal;
  };

  /**
   * TODO: Place order on server. (Factuur)
   */
  $scope.submitOrder = function() {
    var order = {};
    order.debiteur = authenticationService.getAuthenticator().debiteur;
    order.regels = JSON.parse(angular.toJson($scope.cart));

    orderService.create(order, function (data) {
        $location.path('/');
        cartService.empty();
        ngNotify.set('Bedankt voor uw bestelling!', 'success');
    });
  }

});
