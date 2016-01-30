'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:MenuCtrl
 * @description
 * # MenuCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('MenuCtrl', function ($scope, $route, cartService, authenticationService, ROLES) {
    $scope.$route = $route;
    $scope.ROLES = ROLES;

    $scope.cartSize = function() {
      var cart = cartService.retrieve();
      return (cart) ? cart.length : 0;
    };

    $scope.hasRole = function(role) {
    	return authenticationService.hasRole(role);
    }

  });
