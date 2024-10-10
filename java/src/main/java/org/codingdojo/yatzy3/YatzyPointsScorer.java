package org.codingdojo.yatzy3;

import java.util.List;

public class YatzyPointsScorer extends CategoryScorer  {
    private final MathsUtils mathsUtils;

    public YatzyPointsScorer(MathsUtils mathsUtils) {
        this.mathsUtils = mathsUtils;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        if (mathsUtils.frequencies(dice).containsValue(5)) {
            return 50;
        }
        return 0;
    }
}
