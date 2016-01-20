'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:MaakBestellijstCtrl
 * @description
 * # MaakBestellijstCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('MaakBestellijstCtrl', function ($scope, wijnen, actie) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    $scope.actie = [];
    $scope.maakActie = function(){
      $scope.wijnen.forEach(function(wijn, index) {
        if (wijn.active == true){
          $scope.actie.push(wijn);

        }
      });
      console.log($scope.actie)
      actie.send($scope.actie)
    }
    $scope.list = [];
    $scope.wijnen = [];
    $scope.submit = function(){
      if ($scope.wijnen){
        $scope.list.push(this.wijnen);
        $scope.text ='';
      }
    }

    wijnen.all().then(function(data) {
      $scope.wijnen = data;
    }, function() {
      throw Error;
    });

  });
