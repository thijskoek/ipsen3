'use strict';

/**
 * @ngdoc overview
 * @name appApp
 * @description
 * # appApp
 *
 * Main module of the application.
 */
angular
  .module('appApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/wijnen', {
        templateUrl: 'views/wijnen.html',
        controller: 'WijnenCtrl',
        controllerAs: 'wijnen'
      })
      .when('/contact', {
        templateUrl: 'views/contact.html',
        controller: 'ContactCtrl',
        controllerAs: 'contact'
      })
      .when('/profielbewerken', {
          templateUrl: 'views/profielbewerken.html',
          controller: 'ProfielCtrl',
          controllerAs: 'profiel'
      })
      .when('/wachtwoordvergeten', {
        templateUrl: 'views/wachtwoordvergeten.html',
        controller: 'WachtwoordCtrl',
        controllerAs: 'vergeten'
      })
      .when('/wachtwoordherstellen', {
              templateUrl: 'views/wachtwoordherstellen.html',
              controller: 'WachtwoordCtrl',
              controllerAs: 'herstellen'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
