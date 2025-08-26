import time
import problems.page1

solver = problems.page1.P4
start_time = time.time()
answer = solver();
end_time = time.time()
elapsed_ms = int((end_time - start_time) * 1000)
print(f"Answer for {solver.__name__}: {answer} ({elapsed_ms} ms)")
