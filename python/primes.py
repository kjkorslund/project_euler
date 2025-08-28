from extensions import IntExt

class Primes:

  def __init__(self):
    self.knownPrimes = [2, 3, 5, 7, 11]
    self.knownPrimesSet = set(self.knownPrimes)
  
  def __getitem__(self, key):
    while len(self.knownPrimes) <= key: self.__generate_next_prime()
    return self.knownPrimes[key]

  def sequence(self):
    index = 0
    while True:
      while len(self.knownPrimes) <= index: self.__generate_next_prime()
      yield self.knownPrimes[index]
      index += 1

  def is_prime(self, n):
    while self.knownPrimes[-1] < n: self.__generate_next_prime()
    return n in self.knownPrimesSet

  def __generate_next_prime(self):
    n = self.knownPrimes[-1] + 2
    while not self.__is_prime_internal(n): n += 2
    self.knownPrimes.append(n)
    self.knownPrimesSet.add(n)
    return n
  
  def __is_prime_internal(self, n):
    for p in self.knownPrimes:
      if p*p > n: break
      if IntExt.is_multiple_of(n,p): return False
    return True

global_primes = Primes()