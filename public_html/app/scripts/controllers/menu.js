'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:MenuCtrl
 * @description
 * # MenuCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('MenuCtrl', function ($scope, $route, cartService) {
    $scope.$route = $route;

    $scope.cartSize = function () {
      var cart = cartService.retrieve();
      return (cart) ? cart.length : 0;
    };
  });
