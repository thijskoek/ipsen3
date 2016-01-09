'use strict';

describe('Controller: WijnenCtrl', function () {

  // load the controller's module
  beforeEach(module('appApp'));

  var WijnenCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    WijnenCtrl = $controller('WijnenCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(WijnenCtrl.awesomeThings.length).toBe(3);
  });
});
