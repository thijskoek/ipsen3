'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:CartCtrl
 * @description
 * # CartCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('CartCtrl', function ($scope, cartService, $location) {

    $scope.items = cartService.retrieve();
    $scope.cartForm = {};

    $scope.submitCart = function() {
        $location.path('/order');
    };

    $scope.getTotal = function(item) {
      return parseInt(item.aantal) * item.wijn.prijs;
    };

    $scope.getTotalCart = function() {
      var total = 0;
      $scope.items.forEach(function(item, index) {
        total += $scope.getTotal(item);
      });
      return Math.round(total * 100) / 100
    };

    $scope.remove = function(item) {
      $scope.items.splice(item, 1);
    };

    $scope.add = function(item) {
      $scope.items.push(item);
    };

    $scope.$watch('items', function(newVal, oldVal) {
      console.log(newVal);
      cartService.save(newVal);
    });

  });
