'use strict';

/**
 * @ngdoc directive
 * @name appApp.directive:dropdown
 * @description
 * # dropdown
 */
angular.module('appApp')
  .directive('dropdown', function () {
    return {
      restrict: 'A',
      link: function postLink(scope, element, attrs) {
        element.dropdown();
      }
    };
  });
