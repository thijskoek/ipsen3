'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:WijnenCtrl
 * @description
 * # WijnenCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('WijnenCtrl', function ($scope, wijnen, cartService, actieService) {
    var self = this;
    //$scope.wijnen = [];
    $scope.landen = [];
    $scope.jaren = [];
    $scope.types = [];

    // TODO: Refactor to reusable function
    self.fillLandenArray = function() {
      // Wow...
      $scope.wijnen.forEach(function(item, itemIndex) {
        // If landen is empty, add anyway.
        if ($scope.landen.length === 0) {
          $scope.landen.push(item.land);
        } else {
          // Otherwise loop through landen
          $scope.landen.forEach(function(land) {
            // Check if land is unique.
            if (!angular.equals(item.land, land)) {
              $scope.landen.push(item.land);
            }
          });
        }
      });
    };

    self.ArrNoDupe = function(a) {
        var temp = {};
        for (var i = 0; i < a.length; i++)
            temp[a[i]] = true;
        var r = [];
        for (var k in temp)
            r.push(k);
        return r;
    };

    wijnen.all().then(function(data) {
      $scope.wijnen = data;

      self.fillLandenArray()

      $scope.wijnen.forEach(function(wijn, index) {
        $scope.jaren.push(wijn.jaar);
        $scope.types.push(wijn.type);
      });

      $scope.jaren = self.ArrNoDupe($scope.jaren);
      $scope.types = self.ArrNoDupe($scope.types);

    }, function() {
      throw Error;
    });


    $scope.acties = [];

    actieService.all().then(function(data) {
      $scope.acties = data;

      self.fillLandenArray()

      $scope.acties.forEach(function(actie, index)
      {
          $scope.actie.wijnen.forEach(function(wijn, index)
        {
          $scope.jaren.push(wijn.jaar);
          $scope.types.push(wijn.type);

        });
          $scope.jaren = self.ArrNoDupe($scope.jaren);
          $scope.types = self.ArrNoDupe($scope.types);

      });
    }, function() {
      throw Error;
    });



    /**
     * TODO: This is temporary.
     * @param wijn
     */
    $scope.addToCart = function (wijn) {
      cartService.add(wijn);
    };

  });
