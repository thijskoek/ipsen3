'use strict';

/**
 * @ngdoc service
 * @name appApp.orderService
 * @description
 * # orderService
 * Service in the appApp.
 */
angular.module('appApp')
  .service('orderService', function ($http, $q) {

    var url = 'api/order';

    return {
      submitDefOrder: function(order) {
        var deferred = $q.defer();
        $http({
          method: 'POST',
          url: url,
          data: order,
          headers: {'Content-Type': 'application/json'}
        }).then(function(data){
          deferred.resolve(data.data);
        }, function(error){
          deferred.reject(error);
        });

        return deferred.promise;
      }
    };
  });
