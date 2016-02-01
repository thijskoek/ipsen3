'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:DashboardCtrl
 * @description
 * # DashboardCtrl
 * Controller of the appApp
 */
angular.module('appApp')
.controller('DashboardCtrl', function ($scope, ROLES, authenticationService, $location, dashboardService, ngNotify) {
  if (!authenticationService.hasRole(ROLES.MSMANGER)) {
    $location.path('/');
  }

  $scope.labels = [];
  $scope.revenueData = [];
  $scope.invoices = [];
  $scope.revenue = {};
  $scope.year = moment().format('YYYY');
  $scope.limit = 10;

  $scope.data = {
    labels: $scope.labels,
    datasets: [
    {
      label: "Omzet 2016",
      fillColor: "rgba(220,220,220,0.5)",
      strokeColor: "rgba(220,220,220,0.8)",
      highlightFill: "rgba(220,220,220,0.75)",
      highlightStroke: "rgba(220,220,220,1)",
      data: $scope.revenueData
    }]
  };


  var self = this;

  self.init = function() {
    self.getInvoices();
    self.getRevenue();
  };

  self.getInvoices = function() {
    dashboardService.findAll('concept', $scope.year).then(function(data) {
      $scope.invoices = data;
    }, function(error) {
      ngNotify.set('Kon orders niet ophalen! ' + error.data.message, 'error');
    });
  };

  self.getRevenue = function() {
    dashboardService.getRevenue($scope.year).then(function(data) {
      $scope.revenue = data;

      for (var key in data) {
        $scope.labels.push(key);
        $scope.revenueData.push(data[key]);
      }
    }, function(error) {
      console.log(error);
      ngNotify.set('Kon omzet cijfers niet ophalen! ' + error.data.message, 'error');
    });
  };

  $scope.getTotalRevenue = function() {
    var total = 0;
    for (var key in $scope.revenue) {
      total += $scope.revenue[key];
    }
    return total;
  }

  $scope.getInvoiceTotal = function(invoice) {
    var total = 0;
    invoice.factuurregels.forEach(function(regel, index) {
      total += (regel.aantal * regel.wijn.prijs);
    });
    return total;
  };

  $scope.showMoreOrders = function() {
    $scope.limit += 15;
  }

  self.init();

});
