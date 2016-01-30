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
    // AngularJS will instantiate a singleton by calling "new" on this function

    var self = this;
    self.uri = API_URL + 'actie';

    self.create = function(actie, onCreated) {


      $http.post(self.uri, actie).success(onCreated).error(function(message, status) {
        alert('Aanmaken mislukt: ' + message);
      });
    };

    //TODO: get maken die alle wijnen ophaalt bij een actieve actie, findall aanroepen in resource, zie userservice

    //self.getAll = function(onReceived) {
    //  var uri = API_URL + 'maakBestellijst';
    //
    //  $http.get(uri).success(onReceived).error(function(message, status) {
    //    alert('Ophalen mislukt: ' + message);
    //  });
    //};

    return {

      all: function() {
        var deferred = $q.defer();
        $http({
          method: 'GET',
          url: self.uri
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
          url: self.uri + '/' + id
        }).then(function(response){
          return response.data;
        }, function(error){
          return error;
        });
      }

    };
  });
