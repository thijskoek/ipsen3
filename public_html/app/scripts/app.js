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
    'ngTouch',
    'ngNotify'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'ActieCtrl',
        activeTab: 'home'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about',
        activeTab: 'about'
      })
      .when('/shop', {
        templateUrl: 'views/shop.html',
        controller: 'ShopCtrl',
        controllerAs: 'shop',
        activeTab: 'shop'
      })
      .when('/contact', {
        templateUrl: 'views/contact.html',
        controller: 'ContactCtrl',
        controllerAs: 'contact',
        activeTab: 'contact'
      })
      .when('/cart', {
        templateUrl: 'views/cart.html',
        controller: 'CartCtrl',
        controllerAs: 'cart'
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
      .when('/order', {
        templateUrl: 'views/order.html',
        controller: 'OrderCtrl',
        controllerAs: 'orderctrl'
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
      .when('/maakbestellijst', {
        templateUrl: 'views/maakbestellijst.html',
        controller: 'MaakBestellijstCtrl',
        controllerAs: 'maakbestellijst'
      })
      .when('/testactie', {
        templateUrl: 'views/testactie.html',
        controller: 'ActieCtrl',
      })
      .when('/dashboard', {
        templateUrl: 'views/dashboard.html',
        controller: 'DashboardCtrl',
        controllerAs: 'dashboard'
      })
      .when('/wijnen', {
        templateUrl: 'views/wijnen.html',
        controller: 'WijnenCtrl',
        controllerAs: 'wijnen'
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
    BEHEERDER:  'beheerder',
    MSMANGER:   'm&s manager',
    LID:        'lid',
    KLANT:      'klant'
  });
