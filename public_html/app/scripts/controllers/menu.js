'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:MenuCtrl
 * @description
 * # MenuCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('MenuCtrl', function ($scope, $route) {
    $scope.$route = $route;
  });
