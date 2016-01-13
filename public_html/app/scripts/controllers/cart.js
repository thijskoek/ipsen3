'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:CartCtrl
 * @description
 * # CartCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('CartCtrl', function ($scope, wijnen) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $scope.wijnen = [];

    wijnen.all().then(function(data) {
      $scope.wijnen = data;
    }, function() {
      throw Error;
    });
  });
