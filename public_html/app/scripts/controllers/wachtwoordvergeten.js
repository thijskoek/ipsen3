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
        name: 'roy',
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
    });
  }

  $scope.verander_wachtwoord = function() {
    alert($location.search().email);
    if(!$scope.compare()) {
      alert("Wachtwoorden komen niet overeen.");
    } else {
      $http({
        method: 'POST',
        url: API_URL + 'wachtwoord/herstellen',
        params: {
          wachtwoord: $scope.wachtwoord,
          email: $location.search().email
        }
      }).then(function successCallback() {
        alert('Wachtwoord wijziging aangevraagd.');
      });
    }
  }

 });
