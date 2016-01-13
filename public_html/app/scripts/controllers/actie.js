/**
 * Created by Thijs Koek on 1/13/2016.
 */

'use strict';

/**
 *@ngdoc function
 *@name appAp.controller.ActieCtrl
 *@description
 *#ActieCtrl
 *Controller of the appApp
 */

angular.module('appApp')
  .controller('ActieCtrl',  function($scope, wijnen){
    $scope.checkboxModel = {
      value1 : true,
    }
    this.awesomeThings = [
      'HTML5 Bolerplate',
      'AngularJS',
      'Karma'
    ];
    $scope.wijnen =[];

    wijnen.all().then(function(data) {
      $scope.wijnen = data;
    }, function() {
      throw Error;
    });
  });
