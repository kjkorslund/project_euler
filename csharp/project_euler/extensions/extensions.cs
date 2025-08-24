using Project_euler.Utils;

namespace Project_euler.Extensions;

public static class IntExtensions
{
  public static bool IsMultipleOf(this int it, int k) => (it % k) == 0;
  public static bool IsEven(this int it) => (it % 2) == 0;
  public static bool IsOdd(this int it) => (it % 2) != 0;
  public static bool IsPalindromic(this int it) => it.ToString().IsPalindrome();
}

public static class LongExtensions
{
  public static bool IsMultipleOf(this long it, long k) => (it % k) == 0;
  public static bool IsEven(this long it) => (it % 2) == 0;
  public static bool IsOdd(this long it) => (it % 2) != 0;
  public static bool IsPalindromic(this long it) => it.ToString().IsPalindrome();

  public static long FindSmallestPrimeFactor(this long it) =>
    (it <= 1) ? it : Primes.Global.First(p => it.IsMultipleOf(p));

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

public static class StringExtensions
{
  public static bool IsPalindrome(this string it)
  {
    for (int i = 0, j = it.Length - 1; i < j; i++, j--)
    {
      if (it[i] != it[j]) return false;
    }
    return true;
  }
}