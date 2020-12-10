package problems_pg2;

import utils.ArrayUtils;
import utils.DigitUtils;
import utils.PermIter2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * The cube, 41063625 (345^3), can be permuted to produce two other cubes: 56623104 (384^3) and 66430125 (405^3). In
 * fact, 41063625 is the smallest cube which has exactly three permutations of its digits which are also cube.
 * <p>
 * Find the smallest cube for which exactly five permutations of its digits are cube.
 */
public class P62 {
    public static void main(String[] args) {
        HashSet<Long> discoveredCubes = new HashSet<>();
        HashMap<Long, Long> baseLookup = new HashMap<>();
        Set<Long> matchingBases = new TreeSet<>();

        for(long l=1; l<10000; l++) {
            long cube = l*l*l;
            discoveredCubes.add(cube);
            baseLookup.put(cube, l);

            Set<Long> matches = new LinkedHashSet<>();

            for(long discoveredCube : discoveredCubes) {
                if (DigitUtils.isDigitalPermutation(cube, discoveredCube)) {
                    matches.add(discoveredCube);
                }
            }

            if (matches.size() == 5) {
                System.out.println("Starting from " + l + ": " + matches);
                for(long match : matches) {
                    matchingBases.add(baseLookup.get(match));
                }
            }
        }

        System.out.println("Matching bases: " + matchingBases);
    }
}
