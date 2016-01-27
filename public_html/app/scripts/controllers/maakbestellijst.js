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
    /*
     {
     "naam": "Thijs the Great",
     "startdatum": 12213123,
     "einddatum": 212121321
     "wijnen": [
     {
     "id": 1,
     "productnummer": 1234,
     "naam": "Gluwein",
     "jaar": 2015,
     "prijs": 5.95,
     "type": "Rood",
     "land": {
     "id": 2,
     "name": "Frankrijk"
     },
     "rang": 1
     }]
     }
     */
    var currentDate = moment().unix();
    $scope.actie = {};
    $scope.actie.startdatum = currentDate;
    $scope.actie.einddatum = currentDate;
    $scope.wijnen = [];

    $scope.submit = function(){
      var actie = $scope.actie;
      actieService.create(actie, function(data) {
        console.log("JEEEJ");
        // TODO: Doe iets succesvols.
      });
    };

    //$scope.datum = function(){
    //  $scope.datum('#datetimepicker1').data("DateTimePicker").function();
    //}

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

      console.log(wijn);
      console.log($scope.activeWines);
    };

    wijnen.all().then(function(data) {
      $scope.wijnen = data;
    }, function() {
      throw Error;
    });

  });
