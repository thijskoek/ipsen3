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

      console.log(angular.toJson($scope.user));

      userService.create($scope.user, function (message) {
        $location.path('/login');
      });
    }

  });
