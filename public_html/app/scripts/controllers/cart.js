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
    $scope.aantal = 1;

    $scope.getTotal = function(wijn) {

        return parseInt(wijn.aantal) * wijn.prijs;
    }

    $scope.getTotalCart = function() {
      var total = 0;
      //total.isNumber(0);

      $scope.wijnen.forEach(function(wijn, index) {
        total += parseInt(wijn.aantal) * wijn.prijs;
        console.log(parseInt(wijn.aantal) * wijn.prijs);
      })
      return Math.round(total * 100) / 100

    }

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
