package org.codingdojo.yatzy3;

import java.util.List;

public class ChanceScorer extends CategoryScorer {
    private final MathsUtils mathsUtils;

    public ChanceScorer(MathsUtils mathsUtils) {
        this.mathsUtils = mathsUtils;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        return mathsUtils.sum(dice);
    }
}
