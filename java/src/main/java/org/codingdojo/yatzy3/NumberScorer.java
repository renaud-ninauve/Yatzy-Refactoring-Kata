package org.codingdojo.yatzy3;

import java.util.List;

public class NumberScorer extends CategoryScorer  {
    private final MathsUtils mathsUtils;
    private final int number;

    public NumberScorer(MathsUtils mathsUtils, int number) {
        this.mathsUtils = mathsUtils;
        this.number = number;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        return mathsUtils.frequencies(dice).get(number) * number;
    }
}
