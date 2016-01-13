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
      .when('/actie', {
        templateUrl: 'views/actie.html',
        controller: 'ActieCtrl',
        controllerAs: 'actie'
      })
      .when('/bestellijsten', {
        templateUrl: 'views/bestellijsten.html',
        controller: 'BestellijstenCtrl',
        controllerAs: 'bestellijsten'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
