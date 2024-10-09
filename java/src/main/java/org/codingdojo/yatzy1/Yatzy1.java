package org.codingdojo.yatzy1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Yatzy1 {

    public static int chance(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).chance();
    }

    public static int yatzy(int... dice) {
        return dice.length >= 5 ? new Yatzy1(dice[0], dice[1], dice[2], dice[3], dice[4]).yatzy() : 0;
    }

    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).ones();
    }

    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).twos();
    }

    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).threes();
    }

    public static int two_pair(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).two_pair();
    }

    public static int smallStraight(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).smallStraight();
    }

    public static int largeStraight(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).largeStraight();
    }

    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).four_of_a_kind();
    }

    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).three_of_a_kind();
    }

    public static int fullHouse(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).fullHouse();
    }

    public static int score_pair(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy1(d1, d2, d3, d4, d5).score_pair();
    }

    protected int[] dice;

    public Yatzy1() {
    }

    public Yatzy1(int d1, int d2, int d3, int d4, int _5) {
        this();
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = _5;
    }

    public int chance() {
        return Arrays.stream(dice).sum();
    }

    public int ones() {
        return sumOf(1);
    }

    public int twos() {
        return sumOf(2);
    }

    public int threes() {
        return sumOf(3);
    }

    public int fours() {
        return sumOf(4);
    }

    public int fives() {
        return sumOf(5);
    }

    public int sixes() {
        return sumOf(6);
    }

    private int sumOf(int value) {
        return Arrays.stream(dice)
            .filter(d -> d == value)
            .sum();
    }

    private List<Integer> dicesAppearingAtLeast(int times) {
        Map<Integer, Long> countByDice = Arrays.stream(dice)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return countByDice.entrySet().stream()
            .filter(e -> e.getValue() >= times)
            .map(Map.Entry::getKey)
            .toList();
    }

    private int score_n_of_a_kind(int n) {
        List<Integer> nOfAKinds = dicesAppearingAtLeast(n);
        return nOfAKinds.stream()
            .max(Comparator.<Integer>naturalOrder()).orElse(0) * n;
    }

    public int score_pair() {
        return score_n_of_a_kind(2);
    }

    public int two_pair() {
        List<Integer> pairs = dicesAppearingAtLeast(2);
        if (pairs.size() < 2) {
            return 0;
        }
        return pairs.stream()
            .sorted(Comparator.<Integer>naturalOrder().reversed())
            .limit(2)
            .mapToInt(d -> d * 2)
            .sum();
    }

    public int four_of_a_kind() {
        return score_n_of_a_kind(4);
    }

    public int three_of_a_kind() {
        return score_n_of_a_kind(3);
    }

    public int smallStraight() {
        final List<Integer> smallStraight = List.of(1, 2, 3, 4, 5);
        return matches(smallStraight) ? 15: 0;
    }

    private boolean matches(List<Integer> expectedDices) {
        final List<Integer> expectedSorted = new ArrayList<>(expectedDices);
        expectedSorted.sort(Comparator.naturalOrder());
        final List<Integer> actualSorted = Arrays.stream(dice).sorted().boxed().toList();
        return actualSorted.equals(expectedSorted);
    }

    public int largeStraight() {
        final List<Integer> smallStraight = List.of(2, 3, 4, 5, 6);
        return matches(smallStraight) ? 20: 0;
    }

    public int fullHouse() {
        List<Integer> threeOfAKinds = dicesAppearingAtLeast(3);
        if (threeOfAKinds.isEmpty()) {
            return 0;
        }
        int threeOfAKind = threeOfAKinds.stream().max(Comparator.<Integer>naturalOrder()).orElseThrow(() -> new AssertionError("should not happen"));

        List<Integer> pairs = dicesAppearingAtLeast(2);
        return pairs.stream()
            .filter(d -> d != threeOfAKind)
            .max(Comparator.<Integer>naturalOrder())
            .map(pair -> pair * 2 + threeOfAKind * 3)
            .orElse(0);
    }

    public int yatzy() {
        List<Integer> yatzies = dicesAppearingAtLeast(5);
        return yatzies.isEmpty() ? 0 : 50;
    }
}



