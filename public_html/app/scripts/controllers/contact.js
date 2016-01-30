'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:ContactCtrl
 * @description
 * # ContactCtrl
 * Controller of the appApp
 */
angular.module('appApp').controller('ContactCtrl', function ($scope, $http, mailservice) {
    $scope.formModel = {};

        $scope.submitContact = function (valid) {
          if(valid) {
            mailservice.send($scope.formModel).then(function succesCallback(result) {
              $scope.succes = "Bericht verzonden";
            }, function errorCallback(result) {
            });
        } else {
           $scope.error = "U heeft niet alle velden ingevuld";
        }};

  });
