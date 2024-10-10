package org.codingdojo.yatzy3;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class TwoPairsScorer extends CategoryScorer {
    private final MathsUtils mathsUtils;

    public TwoPairsScorer(MathsUtils mathsUtils) {
        this.mathsUtils = mathsUtils;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        Map<Integer, Integer> frequencies = mathsUtils.frequencies(dice);
        int score = 0;
        if (new MathsUtils().frequencies(dice).values().stream().filter(f -> f >= 2).toList().size() == 2) {
            score = Stream.of(6, 5, 4, 3, 2, 1)
                .mapToInt(i -> i)
                .filter(i -> frequencies.get(i) >= 2)
                .map(i -> i * 2)
                .sum();
        }
        return score;
    }
}
