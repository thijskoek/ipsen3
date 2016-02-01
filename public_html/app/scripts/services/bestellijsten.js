'use strict';

/**
 * @ngdoc service
 * @name appApp.bestellijsten
 * @description
 * # bestellijsten
 * Service in the appApp.
 */
angular.module('appApp')
  .service('bestellijsten', function ($http, $q) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    var url = '/api/bestellijst';

    return {

      all: function() {
        var deferred = $q.defer();
        $http({
          method: 'GET',
          url: url
        }).then(function(data){
          deferred.resolve(data.data);
        }, function(error){
          deferred.reject(error);
        });

        return deferred.promise;
      },

      findById: function(id) {
        return $http({
          method: 'GET',
          url: url + '/' + id
        }).then(function(response){
          return response.data;
        }, function(error){
          return error;
        });
      }

    };
  });
