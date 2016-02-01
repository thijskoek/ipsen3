'use strict';

/**
 * @ngdoc directive
 * @name appApp.directive:chart
 * @description
 * # chart
 */
angular.module('appApp')
  .directive('chart', function ($timeout) {
    return {
      templateUrl: 'views/directives/chart.html',
      restrict: 'E',
      scope: {
        chartData: '=',
        chartOptions: '=',
        chartType: '@'
      },
      link: function postLink(scope, element, attrs) {
        var $chart = $('canvas#chart');
        var chart;
        if (scope.chartType === 'bar') {
           chart = new Chart($chart.get(0).getContext('2d')).Bar(scope.chartData, scope.chartOptions);
        }

        scope.$watch('chartData', function(oldVal, newVal) {
          chart.removeData();
          $timeout(function() {
            for (var i = 0; i < newVal.datasets[0].data.length; i++) {
              var dataArr = [];
              dataArr.push(newVal.datasets[0].data[i]);
              chart.addData(dataArr, newVal.labels[i]);
            }
          }, 100);
          chart.update();
        });
      }
    };
  });
