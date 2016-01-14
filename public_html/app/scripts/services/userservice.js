'use strict';

/**
 * @ngdoc service
 * @name appApp.userService
 * @description
 * # userService
 * Service in the appApp.
 */
angular.module('appApp')
  .service('userService', function ($http, API_URL) {

    var self = this;

    self.authenticate = function(onSuccess, onFailure)
    {
      var uri = API_URL + 'users/me';

      $http.get(uri).success(onSuccess).error(onFailure || function(message, status)
      {
        alert('Inloggen mislukt: ' + message);
      });
    };

    self.create = function(name, postcode, streetnumber, email, password, onCreated)
    {
      var uri = API_URL + 'users';
      var data =
      {
        id: id,
        email: email,
        password: password,
        roles: roles
      };

      $http.post(uri, data).success(onCreated).error(function(message, status)
      {
        alert('Aanmaken mislukt: ' + message);
      });
    };

    self.getAll = function(onReceived)
    {
      var uri = API_URL + 'users';

      $http.get(uri).success(onReceived).error(function(message, status)
      {
        alert('Ophalen mislukt: ' + message);
      });
    };
  });
