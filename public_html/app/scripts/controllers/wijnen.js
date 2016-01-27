'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:WijnenCtrl
 * @description
 * # WijnenCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('WijnenCtrl', function ($scope, wijnen, cartService) {
    $scope.wijnen = [];

    wijnen.all().then(function(data) {
      $scope.wijnen = data;
    }, function() {
      throw Error;
    });

    /**
     * TODO: This is temporary.
     * @param wijn
     */
    $scope.addToCart = function (wijn) {
      cartService.add(wijn);
    };

  });
