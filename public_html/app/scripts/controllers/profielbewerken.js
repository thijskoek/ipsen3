'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the appApp
 */
angular.module('appApp').controller('ProfielCtrl', function ($scope, $http) {

    $scope.submit = function(response) {
      $http({
        method: 'GET',
        url: 'api/gebruiker/test',
        headers: {
          'Content-Type': undefined
        }
      }).then(function successCallback(response) {
        console.log(response);
      }, function errorCallback(response, status, headers, config) {
        console.log(response, status, headers, config);
      });
    }
 });
