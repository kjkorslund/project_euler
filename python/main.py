import time
from extensions import IntExt
import problems.page1

solver = problems.page1.P1
start_time = time.time()
answer = solver();
end_time = time.time()
elapsed_ms = int((end_time - start_time) * 1000)
print(f"Answer for {solver.__name__}: {answer} ({elapsed_ms} ms)")

i = IntExt(2)
print(f"{i}: {i.is_even()} / {i.is_odd()}")
i = IntExt(3)
print(f"{i}: {i.is_even()} / {i.is_odd()}")