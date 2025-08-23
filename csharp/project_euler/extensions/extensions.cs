using Project_euler.Utils;

namespace Project_euler.Extensions;

public static class IntExtensions
{
  public static bool IsMultipleOf(this int it, int k)
  {
    return (it % k) == 0;
  }
  public static bool IsEven(this int it)
  {
    return (it % 2) == 0;
  }

  public static bool IsOdd(this int it)
  {
    return (it % 2) != 0;
  }
}

public static class LongExtensions
{
  public static bool IsMultipleOf(this long it, long k)
  {
    return (it % k) == 0;
  }
  public static long FindSmallestPrimeFactor(this long it)
  {
    return (it <= 1) ? it : Primes.Global.First(p => it.IsMultipleOf(p));
  }
  public static IEnumerable<long> FindPrimeFactors(this long it)
  {
    long remainder = it;
    while (remainder > 1)
    {
      long spf = remainder.FindSmallestPrimeFactor();
      yield return spf;
      remainder /= spf;
    }
  }
}