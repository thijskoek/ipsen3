'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('LoginCtrl', function ($scope, authenticationService, userService, $location) {

    $scope.login = function() {
      authenticationService.createAuthentication($scope.email, $scope.password);

      userService.authenticate(function(authenticator)
      {
        authenticationService.setAuthenticator(authenticator);
        authenticationService.storeAuthentication($scope.remember);
        // TODO: Redirect to previous page.
        $location.path('/');
      });

    }

  });
