'use strict';

/**
 * @ngdoc directive
 * @name appApp.directive:animateOnChange
 * @description
 * # animateOnChange
 */
angular.module('appApp')
  .directive('animateOnChange', function ($animate, $timeout) {
    return function(scope, elem, attr) {
    	scope.$watch(attr.animateOnChange, function(newVal, oldVal) {
    		if (newVal!=oldVal) {
    			var cssClass = newVal > oldVal ? 'animated bounce': '';
    			$animate.addClass(elem, cssClass).then(function() {
    				$timeout(function() {
    					$animate.removeClass(elem, cssClass);
    				});
    			});
    		}
    	});
    };
  });
