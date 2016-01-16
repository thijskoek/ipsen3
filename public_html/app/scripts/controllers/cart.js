'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:CartCtrl
 * @description
 * # CartCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('CartCtrl', function ($rootScope, $scope, wijnen, cartService, $location) {

    $scope.cartForm = {};
    $scope.aantal = 1;

    $scope.submitCart = function() {
        $location.path('/order');
    }

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

    $scope.wijnen = [];

    wijnen.all().then(function(data) {
      cartService.save(data);
      $scope.wijnen = data;
    }, function() {
      throw Error;
    });

    $scope.remove = function(index) {

        $scope.wijnen.splice(index, 1);

    }


    $scope.$watch('wijnen', function(newValue, oldValue) {
      console.log(newValue);
      cartService.save(newValue);
    }, true)
  });
