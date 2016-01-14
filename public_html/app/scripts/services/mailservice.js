'use strict';

/**
 * @ngdoc service
 * @name appApp.mailservice
 * @description
 * # mailservice
 * Service in the appApp.
 */
angular.module('appApp')
  .service('mailservice', function ($http, $q, API_URL) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    var url = API_URL + 'mail';

    return {
      send: function(mail) {
        var deferred = $q.defer();
          $http({
            method: 'POST',
            url: url,
            data: mail,
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
