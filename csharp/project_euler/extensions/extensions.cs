using System.Numerics;

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