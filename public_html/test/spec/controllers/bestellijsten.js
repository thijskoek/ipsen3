'use strict';

describe('Controller: BestellijstenCtrl', function () {

  // load the controller's module
  beforeEach(module('appApp'));

  var BestellijstenCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BestellijstenCtrl = $controller('BestellijstenCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(BestellijstenCtrl.awesomeThings.length).toBe(3);
  });
});
