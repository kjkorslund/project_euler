using System.Collections;
using Project_euler.Extensions;

namespace Project_euler.Utils;

public class Primes : IEnumerable<long>
{
  public static readonly Primes Global = new();

  private readonly List<long> _knownPrimes;
  private readonly HashSet<long> _knownPrimesSet;

  public Primes()
  {
    _knownPrimes = new List<long>([2, 3, 5, 7, 11]);
    _knownPrimesSet = [.. _knownPrimes];
  }

  public long this[int key]
  {
    get
    {
      while (_knownPrimes.Count <= key) GenerateNextPrime();
      return _knownPrimes[key];
    }
  }

  public IEnumerator<long> GetEnumerator()
  {
    foreach (long p in _knownPrimes) yield return p;
    while (true) yield return GenerateNextPrime();
  }

  IEnumerator IEnumerable.GetEnumerator()
  {
    return GetEnumerator();
  }

  public bool IsPrime(long n)
  {
    while (_knownPrimes.Last() < n) GenerateNextPrime();
    return _knownPrimesSet.Contains(n);
  }

  private long GenerateNextPrime()
  {
    var n = _knownPrimes.Last() + 2;
    while (!IsPrimeInternal(n)) n += 2;
    _knownPrimes.Add(n);
    _knownPrimesSet.Add(n);
    return n;
  }

  private bool IsPrimeInternal(long n)
  {
    foreach (var p in _knownPrimes.TakeWhile(p => p * p < n))
    {
      if (n.IsMultipleOf(p)) return false;
    }
    return true;
  }

}