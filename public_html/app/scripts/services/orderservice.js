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
      var uri ='order';

      $http.post(uri, order).success(onCreated).error(function(message, status) {
        alert('Aanmaken mislukt: ' + message);
      });
    };

    //return {
    //  submitDefOrder: function(order) {
    //    var deferred = $q.defer();
    //    $http({
    //      method: 'POST',
    //      url: url,
    //      data: order
    //    }).then(function(data){
    //      deferred.resolve(data.data);
    //    }, function(error){
    //      deferred.reject(error);
    //    });
    //
    //    return deferred.promise;
    //  }
    //};
  });
