// Adds prototype extensions to built-in types to implement useful advanced functions for solving Project Euler problems
// This module has no exports

import {Primes} from "./utils.js";

Array.prototype.first = function() {
  return this[0];
}

Array.prototype.last = function() {
  return this[this.length - 1];
}

Number.prototype.foobar = function() {
  return 'foobar: ' + this;
}

// Return true if this number is a multiple of k
Number.prototype.isMultipleOf = function(k) {
  return this%k == 0;
}

Number.prototype.isEven = function() {
  return this%2 == 0;
}

Number.prototype.isOdd = function() {
  return this%2 != 0;
}

Number.prototype.isPrime = function() {
  return Primes.global.isPrime(this);
}

Number.prototype.findSmallestPrimeFactor = function() {
  if (this <= 1) return this;
  return Primes.global.findFirst(it => this.isMultipleOf(it));
}

Number.prototype.findPrimeFactors = function() {
  let primeFactors = [];
  let remainder = this;
  while (remainder > 1) {
    let spf = remainder.findSmallestPrimeFactor();
    primeFactors.push(spf);
    remainder /= spf;
  }
  return primeFactors;
}

Number.prototype.isPalindromic = function() {
  return String(this).isPalindrome();
}

String.prototype.isPalindrome = function() {
  for(let i=0,j=this.length-1; i<j; i++,j--) {
    if(this[i] != this[j]) return false;
  }
  return true;
}