'use strict';

/**
 * @ngdoc service
 * @name appApp.actie
 * @description
 * # actie
 * Service in the appApp.
 */
angular.module('appApp')
  .service('actieService', function ($http, API_URL) {
    // AngularJS will instantiate a singleton by calling "new" on this function

    var self = this;

    self.create = function(actie, onCreated) {
      var uri = API_URL + 'actie';

      $http.post(uri, actie).success(onCreated).error(function(message, status) {
        alert('Aanmaken mislukt: ' + message);
      });
    };
  });


