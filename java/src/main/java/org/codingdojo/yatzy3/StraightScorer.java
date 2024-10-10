package org.codingdojo.yatzy3;

import java.util.List;

public class StraightScorer extends CategoryScorer {
    private final MathsUtils mathsUtils;
    private final int straightIncludes;

    public StraightScorer(MathsUtils mathsUtils, int straightIncludes) {
        this.mathsUtils = mathsUtils;
        this.straightIncludes = straightIncludes;
    }

    boolean isStraight(List<Integer> dice) {
        return mathsUtils.frequencies(dice).values().stream().filter(f -> f == 1).toList().size() == 5;
    }
    @Override
    public int calculateScore(List<Integer> dice) {
        if (isStraight(dice) && mathsUtils.frequencies(dice).get(straightIncludes) != 0) {
            return mathsUtils.sum(dice);
        }
        return 0;
    }
}
