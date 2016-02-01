'use strict';

/**
 * @ngdoc service
 * @name appApp.wijnen
 * @description
 * # wijnen
 * Service in the appApp.
 */
angular.module('appApp')
  .service('wijnen', function ($http, $q, authenticationService) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    var url = '/api/v1/wijnen';

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
      },

      fileUpload: function(id, fd) {
        var deferred = $q.defer();
        $.ajax({
          type: 'POST',
          url: url + '/' + id,
          data: fd,
          processData: false,
          contentType: false,
          beforeSend: function (request) {
            request.setRequestHeader("Authorization", authenticationService.createAuthorizationString());
          },
          success: function(data) {
            deferred.resolve(data);  
          },
          error: function(xhr, textStatus, errorMessage) {
            deferred.reject(textStatus, errorMessage);
          }
        });
        return deferred.promise;
      }

    };
  });
