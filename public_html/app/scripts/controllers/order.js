'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:OrderCtrl
 * @description
 * # OrderCtrl
 * Controller of the appApp
 */

angular.module('appApp').controller('OrderCtrl', function ($scope, cartService, orderService) {
    $scope.wijnen = {};

    $scope.getCart = function() {
       var data = cartService.retrieve();

      $scope.wijnen = data;
    }

  $scope.getCartTotal = function() {
    var total = 0;

    $scope.wijnen.forEach(function(wijn, index) {
      total += parseInt(wijn.aantal) * wijn.prijs;
    })

    return Math.round(total * 100) / 100

  }
  $scope.getBtwPrice = function()
  {
    var btw = 0;

    btw = ($scope.getCartTotal() / 121) * 21;
    return btw;
  }

  $scope.getSubtotaal = function()
  {
    var subtotaal = 0;

    subtotaal = ($scope.getCartTotal() - $scope.getBtwPrice());

    return subtotaal;
  }

  $scope.submitOrder = function() {

    var order = angular.toJson($scope.wijnen);

     orderService.submitDefOrder(order).then(function succesCallback(result) {
       console.log("submit success");
     }, function errorCallback(result) {
       console.log("submit failed");
     });
  }
  });
