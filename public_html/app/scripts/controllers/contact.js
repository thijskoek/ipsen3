'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:ContactCtrl
 * @description
 * # ContactCtrl
 * Controller of the appApp
 */
angular.module('appApp').controller('ContactCtrl', function ($scope, $http) {
    $scope.formModel = {};

        $scope.submitContact = function (valid) {
          if(valid) {
          console.log("form submitted");
          console.log($scope.formModel);

          //var postObject = new Object();
          //postObject.name = $scope.formModel.name
          //postObject.email = $scope.formModel.email;
          //postObject.subject = $scope.formModel.subject;
          //postObject.message = $scope.formModel.message;

          $http({
            method: 'POST',
            url: 'api/email',
            data: $scope.formModel,
            dataType: 'json',
            headers: {
              "Content-Type": "application/json"
            }
          }).succes(function(response) {
            $scope.response = response;
          }).error(function(error) {
            $scope.error = error;
          });

    } else {
        console.log("invalid!");
    }
        };
  });
