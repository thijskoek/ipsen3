'use strict';

/**
 * @ngdoc function
 * @name appApp.controller:WijnenCtrl
 * @description
 * # WijnenCtrl
 * Controller of the appApp
 */
angular.module('appApp')
  .controller('WijnenCtrl', function ($scope, wijnen, ngNotify, $routeParams, authenticationService, ROLES, $location, 
  	$timeout) {
  	if (!authenticationService.hasRole(ROLES.MSMANGER)) {
  		$location.path('/');
  	}

    $scope.wijnen = [];

    if($routeParams.id) {
    	wijnen.findById($routeParams.id).then(function(data) {
	    	$scope.wijnen = data;
	    }, function(error) {
	    	ngNotify('Kon wijnen niet ophalen! ' + error.error.message, 'error');
	    });
    } else {
    	wijnen.all().then(function(data) {
	    	$scope.wijnen = data;
	    }, function(error) {
	    	ngNotify('Kon wijnen niet ophalen! ' + error.error.message, 'error');
	    });
    }

    $scope.save = function() {
    	var file = $('#wijnForm input[name=file]').get(0).files[0];
    	var fd = new FormData();
    	fd.append('file', file);
    	if (file) {
    		wijnen.fileUpload($scope.wijnen.id, fd).then(function(data) {
	    		$scope.wijnen = data;
	    		ngNotify.set("De wijn is bijgewerkt", "success");
	    	}, function(error) {
	    		ngNotify.set("Error " + error.error.message, "error");
	    		console.error(error);
	    	});
    	}
    };

  });
