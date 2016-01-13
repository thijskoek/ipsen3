'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the appApp
 */

 /*
  Created by Roy on 12-1-2016.
 */
angular.module('appApp').controller('ProfielCtrl', function ($scope, $http) {

    //Submit function.
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

    //Set id function.
    $scope.set_id = function() {
      console.log($scope.id);
      $http({
        method: 'GET',
        url: 'api/gebruiker/getGebruiker',
        params: {
          id: $scope.id
        }
      }).then(function successCallback(response) {
        //console.log(response.data)
        $scope.set_velden(response.data);
      }, function errorCallback(response) {
        console.log(response);
      });
    }

    //Fill the input fields with fetched data.
    //Two way binding ftw.
    $scope.set_velden = function(gebruiker) {
      for(var property in gebruiker) {
        $scope[property] = gebruiker[property];
      }
    }
 });
