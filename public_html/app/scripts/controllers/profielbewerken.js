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
angular.module('appApp').controller('ProfielCtrl', function ($scope, $http, API_URL, $location) {

    $scope.gewijzigd = false;
    $scope.foutmelding = false;
    $scope.personen = [
      'jaap',
      'henk',
      'klaas'
    ];

    $scope.haal_factuur = function(id) {
      $http({
        method: 'GET',
        url: API_URL + 'bestelling/haalfactuur',
        params: {
          id: $location.search().f
        }
      }).then(function successCallback(response) {
        $scope.factuurregels = response.data['factuurregels'];
        for(var i = 0; i < $scope.factuurregels.length; i++) {
          console.log(console.log($scope.factuurregels[i]));
        }
      })
    }

    $scope.open_factuuroverzicht = function() {
      $location.url('/besteloverzicht');
    }

    //Submit function.
    $scope.submit = function() {
      $http({
        method: 'POST',
        url: API_URL + 'gebruiker/wijzig',
        params: {
          gebruiker: $scope.fetch_gebruiker()
        }
      }).then(function successCallback(response) {
        $scope.gewijzigd = true;
      }, function errorCallback(response) {
        $scope.foutmelding = true;
      });
    }

    //Set id function.
    $scope.laad_gebruiker = function() {
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
    $scope.prijs = function(i) {
      console.log($scope.factuurs[0][0]);
    }

    $scope.open_factuur = function(id) {
      $location.url('/factuur?f=' + id);
    }

    $scope.test = function() {
      $http({
          method: 'POST',
          url: API_URL + 'bestelling/test'
        }).then(function successCallback(response) {
          $scope.factuurs = response.data;
          console.log(response.data);
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

    $scope.terug = function() {
      $location.url('/');
    }
 });
