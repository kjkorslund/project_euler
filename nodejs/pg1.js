const pg1 = {};
export default pg1;

import {Range} from "./utils.js";

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

  let sum = 0;
  Range.open(1,1000).foreach(i => {
    if (i.isMultipleOf(3) || i.isMultipleOf(5)) {
      sum += i;
    }
  });
  console.log(sum.foobar())
  return sum;
}