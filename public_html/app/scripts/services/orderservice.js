'use strict';

/**
 * @ngdoc service
 * @name appApp.orderService
 * @description
 * # orderService
 * Service in the appApp.
 */
angular.module('appApp')
  .service('orderService', function ($http, API_URL) {

    var self = this;

    self.create = function(order, onCreated) {
      var uri = API_URL + "order";

      $http.post(uri, order).success(onCreated).error(function(message, status) {
        alert('Aanmaken mislukt: ' + message);
      });
    };

  });
