'use strict';

describe('Service: wijnen', function () {

  // load the service's module
  beforeEach(module('appApp'));

  // instantiate service
  var wijnen;
  beforeEach(inject(function (_wijnen_) {
    wijnen = _wijnen_;
  }));

  it('should do something', function () {
    expect(!!wijnen).toBe(true);
  });

});
