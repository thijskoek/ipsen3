'use strict';

/**
 * @ngdoc service
 * @name appApp.actie
 * @description
 * # actie
 * Service in the appApp.
 *
 */

angular.module('appApp')
  .service('actieService', function ($http, API_URL, $q) {

    var url = API_URL + 'actie';
    // AngularJS will instantiate a singleton by calling "new" on this function

    //var self = this;
    //
    //self.create = function(actie, onCreated) {
    //  var uri = API_URL + 'actie';
    //
    //  $http.post(uri, actie).success(onCreated).error(function(message, status) {
    //    alert('Aanmaken mislukt: ' + message);
    //  });
    //};
    //
    ////TODO: get maken die alle wijnen ophaalt bij een actieve actie, findall aanroepen in resource, zie userservice
    //
    //self.all = function(onReceived) {
    //  var uri = API_URL + 'actie';
    //
    //  $http.get(uri).success(onReceived).error(function(message, status) {
    //    alert('Ophalen mislukt: ' + message);
    //  });
    //};

    return {

    create: function(actie) {
      var deferred = $q.defer();
      $http({
        method: 'POST',
        url: url,
        data: actie,
        headers: {'Content-Type': 'application/json'}
      }).then(function(data){
        deferred.resolve(data.data);
      }, function(error){
        deferred.reject(error);
      });

      return deferred.promise;
    },

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
