
from extensions import IntExt

# [Problem 1](https://projecteuler.net/problem=1)
# 
# If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
# The sum of these multiples is 23.
# 
# Find the sum of all the multiples of 3 or 5 below 1000.
def P1():
  sum = 0
  for i in range(1, 1000):
    i = IntExt(i)
    if i.is_multiple_of(3) or i.is_multiple_of(5):
      sum += i
  return sum