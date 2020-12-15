package problems

fun Int.isMultipleOf(n: Int) = (this % n == 0)
fun Int.isEven() = isMultipleOf(2);
