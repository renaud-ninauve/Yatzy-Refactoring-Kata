package org.codingdojo.yatzy3;

import java.util.List;
import java.util.Map;

public class FullHouseScorer implements  CategoryScorer {
    private final MathsUtils mathsUtils;

    public FullHouseScorer(MathsUtils mathsUtils) {
        this.mathsUtils = mathsUtils;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        Map<Integer, Integer> frequencies = mathsUtils.frequencies(dice);
        if (frequencies.containsValue(2) && frequencies.containsValue(3)) {
            return mathsUtils.sum(dice);
        }
        return 0;
    }
}

