'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the appApp
 */
angular.module('appApp').controller('ProfielbewerkenCtrl', function ($scope) {
 
    $scope.bewerkProfiel = function() {
      alert($scope.naam);
    };
 });
