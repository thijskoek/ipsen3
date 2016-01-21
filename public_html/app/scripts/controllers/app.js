'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:AppCtrl
 * @description
 * # AppCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('AppCtrl', function ($scope, $location, authenticationService, cartService) {

    $scope.logout = function()
    {
      authenticationService.deleteAuthentication();
      $location.path('/');
    };

  });
