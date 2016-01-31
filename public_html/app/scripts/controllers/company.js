'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:CompanyCtrl
 * @description
 * # CompanyCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('CompanyCtrl', function ($scope, $http, API_URL) {


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

    $scope.loadCompany = function() {
      $http({
        method: 'GET',
        url: API_URL + 'bedrijf/getBedrijf'
      }).then(function successCallback(response) {
        console.log(response.data)
        $scope.set_velden(response.data);
      }, function errorCallback(response) {
        console.log(response);
      });
    }

    $scope.fetch_company = function() {
      var company = {
        bedrijfsnaam: $scope.bedrijfsnaam,
        adres: $scope.adres,
        woonplats: $scope.woonplaats,
        postcode: $scope.postcode,
        email: $scope.email,
        telefoon: $scope.telefoon,
        kvk: $scope.kvk,
        btwnummer: $scope.btwnummer,
        iban: $scope.iban,
        bic: $scope.bic
      };
      return company;
    }

    $scope.set_velden = function(company) {
      for(var property in company) {
        $scope[property] = company[property];
      }
    }
  });
