package util

class FibonacciGenerator : Iterable<Int> {
    var previous: Int = -1;
    var current: Int = -1;

    fun peek(): Int = current;
    fun next(): Int {
        when {
            current < 0 -> {
                current = 1;
                return 1;
            }
            current == 1 -> {
                previous = 1;
                current = 2;
            }
            else -> {
                val next = previous + current;
                previous = current;
                current = next;
            }
        }
        return current;
    }

    override fun iterator(): Iterator<Int> {
        return object: IntIterator() {
            override fun hasNext() = true;
            override fun nextInt() = this@FibonacciGenerator.next();
        }
    }
}