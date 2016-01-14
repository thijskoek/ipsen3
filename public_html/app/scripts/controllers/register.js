'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('RegisterCtrl', function ($scope, $location, userService) {

    $scope.registerUser = function (valid) {
      if (!valid) {
        // TODO: Show error message.
        return false;
      }

      var data = $scope.user;
      data.debiteur.email = data.email;

      userService.create(data, function () {
        $location.path('/login');
      });
    };

  });
