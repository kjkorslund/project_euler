namespace Project_euler.Problems;

interface IProblem<T>
{
  /// <summary>
  /// Solve the problem and return the result
  /// </summary>
  /// <returns>Solution result (type may vary)</returns>
  T Solve();
}