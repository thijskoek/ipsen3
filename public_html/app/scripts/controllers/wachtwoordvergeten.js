'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the appApp
 */

 /*
  Created by Roy on 12-1-2016.
 */
angular.module('appApp').controller('WachtwoordCtrl', function ($scope, $http) {

  $scope.test = function() {
    alert("yeye");
  }

  $scope.compare = function() {
    if($scope.wachtwoord != $scope.bevestiging) {
      return false;
    } else {
      return true;
    }
  }

  $scope.wijzig = function() {
    if(!$scope.compare()) {
      alert('Wachtwoorden komen niet overeen.');
    } else {
      alert('oke');
    }
  }

 });
