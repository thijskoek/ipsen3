'use strict';

/**
 * @ngdoc service
 * @name appApp.actie
 * @description
 * # actie
 * Service in the appApp.
 */
angular.module('appApp')
  .service('actie', function ($http, $q) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    var url = 'api/maakbestellijst';
    return {
      send: function(actie) {
        var deferred = $q.defer();
        $http({
          method: 'POST',
          url: url,
          data: actie,
          headers: {'Content-Type': 'application/json'}
        }).then(function(data){
          deferred.resolve(data.data);
          console.log("succes");
        }, function(error){
          deferred.reject(error);
          console.log("failed");
        });

        return deferred.promise;
      }
    };
  });


