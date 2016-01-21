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
        controllerAs: 'main',
        activeTab: 'home'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about',
        activeTab: 'about'
      })
      .when('/wijnen', {
        templateUrl: 'views/wijnen.html',
        controller: 'WijnenCtrl',
        controllerAs: 'wijnen',
        activeTab: 'wijnen'
      })
      .when('/contact', {
        templateUrl: 'views/contact.html',
        controller: 'ContactCtrl',
        controllerAs: 'contact',
        activeTab: 'contact'
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
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl',
        activeTab: 'login'
      })
      .when('/register', {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl',
        controllerAs: 'register'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .config(function($httpProvider) {
    $httpProvider.interceptors.push('requestService');

    if(!$httpProvider.defaults.headers.get) {
      $httpProvider.defaults.headers.get = {};
    }
  })
  .constant('API_URL', "/api/v1/")
  .constant('ROLES', {
    BEHEERDER: 'beheerder',
    MSMANGER: 'm&s manager',
    LID: 'lid',
    KLANT: 'klant'
  });
