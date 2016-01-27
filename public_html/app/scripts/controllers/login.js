'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:LoginCtrl
 * @author Daan Rosbergen
 * @description
 * # LoginCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('LoginCtrl', function ($scope, authenticationService, userService, $location, ROLES) {

    var self = this;

    /**
     * Try to login the user.
     *
     * @param valid
     * @returns {boolean}
     */
    $scope.login = function (valid) {
      if (!valid) {
        return false;
      }
      authenticationService.createAuthentication($scope.email, $scope.password);

      userService.authenticate(function (authenticator) {
        authenticationService.setAuthenticator(authenticator);
        authenticationService.storeAuthentication($scope.remember);
        self.redirectUser(authenticationService.getAuthenticator());
      }, function(message, status) {
        $scope.error = "Inloggen is niet gelukt, probeer het opnieuw."
      });
    };

    /**
     * Redirect user according to its role.
     *
     * TODO: Denk niet dat dit werkt.
     * @param user
     */
    self.redirectUser = function (user) {
      user.roles.forEach(function (role, index) {
        if (role.name === ROLES.BEHEERDER) {
          $location.path('/');
        } else if (role.name === ROLES.MSMANGER) {
          $location.path('/');
        } else if (role.name === ROLES.LID) {
          $location.path('/');
        } else if (role.name === ROLES.KLANT) {
          $location.path('/');
        }
      });

    };

  });
