class IntExt(int):
  def is_even(self): return self % 2 == 0
  def is_odd(self): return self % 2 != 0
  def is_multiple_of(self, other): return self % other == 0