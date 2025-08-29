import time
import problems.page1

# NOTE: recommend using pypy as the interpreter, as many of the solvers have
#       nested loops that run significantly faster with Pypy's JIT compiler
solver = problems.page1.P10
start_time = time.time()
answer = solver();
end_time = time.time()
elapsed_ms = int((end_time - start_time) * 1000)
print(f"Answer for {solver.__name__}: {answer} ({elapsed_ms} ms)")
