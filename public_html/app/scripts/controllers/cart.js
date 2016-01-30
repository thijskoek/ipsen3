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
      if ($scope.authenticated) {
        $location.path('/order');
      } else {
        $location.path('/login');
      }
    };

    $scope.getTotal = function(item) {
      return parseInt(item.aantal) * item.wijn.prijs;
    };

    $scope.getTotalCart = function() {
      var total = 0;
      $scope.items.forEach(function(item) {
        total += $scope.getTotal(item);
      });
      return Math.round(total * 100) / 100;
    };

    $scope.remove = function(item) {
      cartService.remove(item);
      $scope.items = cartService.retrieve();
    };

    $scope.save = function() {
      cartService.save($scope.items);
    };

  });
