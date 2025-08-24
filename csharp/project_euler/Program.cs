// Project Euler in CSharp (console app)
using System.Diagnostics;
using Project_euler.Problems;

var problem = new P7();
var s = Stopwatch.StartNew();
var answer = problem.Solve();
s.Stop();
Console.WriteLine($"Answer for {problem.GetType().Name}: {answer} ({s.ElapsedMilliseconds} ms)");
