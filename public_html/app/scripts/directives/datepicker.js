'use strict';

/**
 * @ngdoc directive
 * @name appApp.directive:datepicker
 * @description
 * # datepicker
 */
angular.module('appApp')
  .directive('datepicker', function () {
    return {
      restrict: 'AE',
      require: 'ngModel',
      scope: {
        datepicker: '=ngModel'
      },
      link: function postLink(scope, element, attrs) {
        $(element).datetimepicker();
        $(element).on('dp.change', function(event) {
          scope.datepicker = event.date.unix();
          scope.$apply();
        });
      }
    };
  });
