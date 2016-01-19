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
angular.module('appApp').controller('ProfielCtrl', function ($scope, $http, API_URL) {

    //Submit function.
    $scope.submit = function() {
      $http({
        method: 'POST',
        url: API_URL + 'gebruiker/wijzig',
        params: {
          gebruiker: $scope.fetch_gebruiker()
        }
      })
    }

    //Set id function.
    $scope.set_id = function() {
      console.log($scope.id);
      $http({
        method: 'GET',
        url: API_URL + 'gebruiker/getGebruiker',
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

    //Fetch gebruiker data from the inputform and return it.
    $scope.fetch_gebruiker = function() {
      var gebruiker = {
        aanhef: $scope.aanhef,
        voornaam: $scope.voornaam,
        tussenvoegsel: $scope.tussenvoegsel,
        naam: $scope.naam,
        adres: $scope.adres,
        woonplaats: $scope.woonplaats,
        postcode: $scope.postcode,
        email: $scope.email,
        telefoon: $scope.telefoon
      };
      return gebruiker;
    }

    //Fill the input fields with fetched data.
    //Two way binding ftw.
    $scope.set_velden = function(gebruiker) {
      for(var property in gebruiker) {
        $scope[property] = gebruiker[property];
      }
    }
 });
