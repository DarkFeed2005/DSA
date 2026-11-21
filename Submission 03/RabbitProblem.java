/**
 * Fibonacci's Rabbit Problem.
 *
 * Rules: start with one newborn pair in month 1. A pair can breed once it is at least
 * one month old, and then produces one new pair every month. Rabbits never die.
 * How many pairs are there in month n?   1, 1, 2, 3, 5, 8, 13, ...
 *
 * Three solutions are given:
 *   1. Recursive            : O(2^n) time  - clear but very slow for big n
 *   2. Iterative            : O(n) time, O(1) space
 *   3. Age-group simulation : O(n * lifespan) - also handles breeding age, litter size
 *                             and mortal rabbits
 * Results use long, so keep n <= 90 to avoid overflow.
 */
public class RabbitProblem {

    // 1. Recursive: pairs(n) = pairs(n-1) (alive last month) + pairs(n-2) (new babies)
    public static long recursive(int n) {
        if (n <= 0) return 0;
        if (n <= 2) return 1;
        return recursive(n - 1) + recursive(n - 2);
    }

    // 2. Iterative: keep only the last two months
    public static long iterative(int n) {
        if (n <= 0) return 0;
        long previous = 1; // month 1
        long current = 1;  // month 2
        for (int month = 3; month <= n; month++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    /**
     * 3. Simulation by age group. pop[i] = number of pairs that are i months old.
     *
     * @param months    month to report (month 1 = one newborn pair)
     * @param breedAge  age in months at which a pair starts breeding (classic problem: 1)
     * @param litter    pairs produced by each breeding pair per month (classic problem: 1)
     * @param lifespan  pairs die on reaching this age; 0 means rabbits never die.
     *                  Must be 0 or greater than breedAge.
     */
    public static long simulate(int months, int breedAge, int litter, int lifespan) {
        if (months <= 0) return 0;
        if (breedAge < 1) throw new IllegalArgumentException("breedAge must be at least 1");
        if (lifespan != 0 && lifespan <= breedAge) {
            throw new IllegalArgumentException("lifespan must be 0 or greater than breedAge");
        }

        // Immortal rabbits: the last bucket collects every pair of age >= breedAge.
        int buckets = (lifespan > 0) ? lifespan : breedAge + 1;
        long[] pop = new long[buckets];
        pop[0] = 1; // month 1

        for (int month = 2; month <= months; month++) {
            long adults = 0;
            for (int age = breedAge; age < buckets; age++) adults += pop[age];
            long newborn = adults * litter;

            if (lifespan == 0) {
                pop[buckets - 1] += pop[buckets - 2]; // pairs keep ageing inside the last bucket
                for (int age = buckets - 2; age >= 1; age--) pop[age] = pop[age - 1];
            } else {
                for (int age = buckets - 1; age >= 1; age--) pop[age] = pop[age - 1]; // oldest die
            }
            pop[0] = newborn;
        }

        long total = 0;
        for (long count : pop) total += count;
        return total;
    }

    public static void main(String[] args) {
        System.out.println("Classic rabbit problem (breed at 1 month, 1 pair per litter, no deaths)");
        System.out.printf("%-7s %-12s %-12s %-12s%n", "Month", "Recursive", "Iterative", "Simulation");
        for (int month = 1; month <= 12; month++) {
            System.out.printf("%-7d %-12d %-12d %-12d%n",
                    month, recursive(month), iterative(month), simulate(month, 1, 1, 0));
        }

        System.out.println();
        System.out.println("Month 50 (iterative): " + iterative(50));

        System.out.println();
        System.out.println("Variant: each pair breeds from 1 month old, but dies at 4 months old");
        for (int month = 1; month <= 10; month++) {
            System.out.println("Month " + month + ": " + simulate(month, 1, 1, 4) + " pairs");
        }
    }
}
