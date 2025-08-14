// Adds prototype extensions to built-in types to implement useful advanced functions for solving Project Euler problems
// This module has no exports

Number.prototype.foobar = function() {
  return 'foobar: ' + this;
}

// Return true if this number is a multiple of k
Number.prototype.isMultipleOf = function(k) {
  return this%k == 0;
}