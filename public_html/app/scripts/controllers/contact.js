'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:ContactCtrl
 * @description
 * # ContactCtrl
 * Controller of the appApp
 */
angular.module('appApp').controller('ContactCtrl', function ($scope) {

    $scope.submitContact = function() {
      alert('bericht is verzonden');
    };

  });
