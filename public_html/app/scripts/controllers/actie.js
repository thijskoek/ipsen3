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

  .controller('ActieCtrl',  function($scope, wijnen, cartService, actieService){

    $scope.checkboxModel = {
      value1 : true,
    }

    //$scope.wijnen =[];
    //
    //wijnen.all().then(function(data) {
    //  $scope.wijnen = data;
    //
    //}, function() {
    //  throw Error;
    //});

    $scope.acties = [];

    actieService.all().then(function(data) {
      $scope.acties = data;
      console.log("Actie: " + data)
    }, function() {
      throw Error;
    });

    $scope.addToCart = function (wijn) {
      cartService.add(wijn);
    };

  });
