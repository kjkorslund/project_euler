package util

import util.extensions.findPrimeFactors

class LongFraction(val numerator: Long, val denominator: Long) {
    fun reduce(): LongFraction {
        val nFactors = numerator.findPrimeFactors()
        var gcf = 1L
        for (nFactor in nFactors) {
            val candidate = gcf * nFactor
            if (denominator % candidate == 0L) {
                gcf = candidate
            }
        }
        return LongFraction(numerator/gcf, denominator/gcf)
    }

    fun equivalentTo(other: LongFraction) = this.reduce() == other.reduce()

    operator fun times(factor: Long) = LongFraction(
        this.numerator * factor, this.denominator * factor
    )

    operator fun times(other: LongFraction) = LongFraction(
        this.numerator * other.numerator, this.denominator * other.denominator
    ).reduce()

    override fun equals(other: Any?): Boolean {
        return other is LongFraction
                && numerator == other.numerator
                && denominator == other.denominator
    }

    override fun hashCode(): Int {
        var result = numerator.hashCode()
        result = 31 * result + denominator.hashCode()
        return result
    }

    override fun toString(): String {
        return "$numerator/$denominator"
    }
}