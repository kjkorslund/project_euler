const pg1 = {};
export default pg1;

/**
 * [Problem 1](https://projecteuler.net/problem=1)
 * 
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23.
 * 
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
pg1.p1 = function() {
  // Note:  zero is not considered a natural number in this problem

  // Return true if n is a multiple of k
  function isMultipleOf(n, k) {
    return n%k == 0;
  }
  
  let sum = 0;
  for (let i=1; i<1000; i++) {
    if (isMultipleOf(i, 3) || isMultipleOf(i, 5)) {
      sum += i;
    }
  }
  return sum;
}