'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:BestellijstenCtrl
 * @description
 * # BestellijstenCtrl
 * Controller of the appApp
 */

angular.module('appApp')
  .controller('BestellijstenCtrl', function ($scope, bestellijsten) {
  this.awesomeThings = [
  'HTML5 Boilerplate',
  'AngularJS',
  'Karma'
  ];

  $scope.bestellijsten = [];

  bestellijsten.all().then(function(data) {
    $scope.bestellijsten = data;
    }, function() {
      throw Error;
    });

    function Cntrl ($scope,$location) {
      $scope.changeView = function(view){
        $location.path(view); // path not hash
      }
    }
  });
