'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:MaakBestellijstCtrl
 * @description
 * # MaakBestellijstCtrl
 * Controller of the appApp
 * Made by Thijs Koek
 */
angular.module('appApp')
  .controller('MaakBestellijstCtrl', function ($scope, wijnen, actieService) {
    var currentDate = moment().unix();

    $scope.actie = {};
    $scope.actie.startdatum = currentDate;
    $scope.actie.einddatum = currentDate;
    $scope.actie.wijnen = [];
    $scope.wijnen = [];

    $scope.submit = function(){
      var actie = {}

      actie = JSON.parse(angular.toJson($scope.actie));
      console.log(actie);

      actieService.create(actie, function(data) {
        console.log(data);
        //$scope.submit(data)
      });
    };

    $scope.toggleWijn = function(wijn) {
      // TODO: Check if wijn is already in
      // acties.
      //- If in acties, remove from acties
      var index = $scope.actie.wijnen.indexOf(wijn);
      if (index === -1) {
        $scope.actie.wijnen.push(wijn);
      } else {
        $scope.actie.wijnen.splice(index, 1);
      }
    };

    wijnen.all().then(function(data) {
      $scope.wijnen = data;
    }, function() {
      throw Error;
    });

  });
