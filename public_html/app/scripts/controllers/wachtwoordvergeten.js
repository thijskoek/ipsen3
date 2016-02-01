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
angular.module('appApp').controller('WachtwoordCtrl', function ($scope, $http, mailservice, API_URL, $location) {
  $scope.verstuurd = false;
  $scope.foutemail = false;
  $scope.gewijzigd = false;
  $scope.foutesleutel = false;

  $scope.test = function() {
    var email = $location.search().email;
    alert(email);
  }

  $scope.compare = function() {
    if($scope.wachtwoord != $scope.bevestiging) {
      return false;
    } else {
      return true;
    }
  }

  $scope.stuur_link = function(link) {
      var mail = {
        name: 'Lions',
        subject: 'Wachtwoord vergeten',
        message: link,
        email: $scope.email
      };
      mailservice.send(mail);
  }

  $scope.generate_link = function() {
    var link;
    $http({
      method: 'POST',
      url: API_URL +  'wachtwoord/generateLink',
      params: {
        email: $scope.email
      }
    }).then(function successCallback(response) {
      link = response.data;
      $scope.stuur_link(link);
      $scope.verstuurd = true;
      $scope.foutemail = false;
    }, function errorCallback(response) {
      $scope.foutemail = true;
      $scope.verstuurd = false;
    });
  }

  $scope.controleer_sleutel = function() {
    $http({
      method: 'POST',
      url: API_URL + 'wachtwoord/controleersleutel',
      params: {
        sleutel: $location.search().key
      }
    }).then(function successCallback(response) {
      if(response.data == false) {
        $location.url('/'); //Invalid key, return to index.
      }
    })
  }

  $scope.verander_wachtwoord = function() {
    if(!$scope.compare()) {
      alert("Wachtwoorden komen niet overeen.");
    } else {
      $http({
        method: 'POST',
        url: API_URL + 'wachtwoord/herstellen',
        params: {
          sleutel: $location.search().key,
          wachtwoord: $scope.wachtwoord
        }
      }).then(function successCallback() {
        $scope.gewijzigd = true;
        $scope.foutesleutel = false;
      }, function errorCallback(response) {
        $scope.gewijzigd = false;
        $scope.foutesleutel = true;
      });
    }
  }

 });
