'use strict';

describe('Service: bestellijsten', function () {

  // load the service's module
  beforeEach(module('appApp'));

  // instantiate service
  var bestellijsten;
  beforeEach(inject(function (_bestellijsten_) {
    bestellijsten = _bestellijsten_;
  }));

  it('should do something', function () {
    expect(!!bestellijsten).toBe(true);
  });

});
