'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:CartCtrl
 * @description
 * # CartCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('CartCtrl', function ($scope, wijnen) {

    $scope.cartForm = {};
    $scope.aantal = 0;

    $scope.checkAantal = function(aantal) {
        if(aantal == null) {
          return 1;
        } else {
          return aantal;
        }
    }

    $scope.submitCart = function() {
       console.log($scope.cartForm)
    }
    $scope.wijnen = [];

    wijnen.all().then(function(data) {
      $scope.wijnen = data;
    }, function() {
      throw Error;
    });
  });
