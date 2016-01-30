'use strict';

/**
 * @ngdoc service
 * @name appApp.dashboardService
 * @description
 * # dashboardService
 * Service in the appApp.
 */
angular.module('appApp')
.service('dashboardService', function ($http, API_URL, $q) {
  var self = this;

  self.findAll = function(status, jaar) {
    var deferred = $q.defer();
    $http({
      method: 'GET',
      url: API_URL + 'invoices',
      params: {status: status, year: jaar}
    }).then(function(data) {
      deferred.resolve(data.data);
    }, function(error) {
      deferred.reject(error);
    });
    return deferred.promise;
  };

  self.getRevenue = function(jaar) {
    var deferred = $q.defer();
    $http({
      method: 'GET',
      url: API_URL + 'invoices/revenue',
      params: {year: jaar}
    }).then(function(data) {
      deferred.resolve(data.data);
    }, function(error) {
      deferred.reject(error);
    });
    return deferred.promise;
  }


});
