class IntExt(int):
  def is_even(self): return self % 2 == 0
  def is_odd(self): return self % 2 != 0
  def is_multiple_of(self, other): return self % other == 0

  def find_smallest_prime_factor(self):
    from primes import global_primes as primes
    sequence = primes.sequence()
    p = next(sequence)
    while not self.is_multiple_of(p):
      p = next(sequence)
    return p
  
  def find_prime_factors(self):
    results = []
    remainder = IntExt(self)
    while remainder > 1:
      pf = remainder.find_smallest_prime_factor()
      results.append(pf)
      remainder = IntExt(remainder/pf)
    return results
