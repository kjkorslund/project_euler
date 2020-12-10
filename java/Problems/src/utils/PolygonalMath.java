package utils;

public class PolygonalMath {
    public static long triangle(long n) {
        return n*(n+1)/2;
    }

    public static long square(long n) {
        return n*n;
    }

    public static long pentagonal(long n) {
            return n*(3*n-1)/2;
    }

    public static long hexagonal(long n) {
        return n*(2*n-1);
    }

    public static long heptagonal(long n) {
        return n*(5*n-3)/2;
    }

    public static long octagonal(long n) {
        return n*(3*n-2);
    }
}
