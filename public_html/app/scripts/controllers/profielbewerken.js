'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the appApp
 */
angular.module('appApp').controller('ProfielCtrl', function ($scope) {

    $scope.submit = function() {
      alert($scope.naam);
    };
 });
