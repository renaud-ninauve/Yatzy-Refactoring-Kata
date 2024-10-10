package org.codingdojo.yatzy3;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RepeatedCountScorer implements  CategoryScorer  {
    private final MathsUtils mathsUtils;
    private final int count;

    public RepeatedCountScorer(MathsUtils mathsUtils, int count) {
        this.mathsUtils = mathsUtils;
        this.count = count;
    }

    @Override
    public int calculateScore(List<Integer> dice) {
        Map<Integer, Integer> frequencies = mathsUtils.frequencies(dice);
        for (int i : Arrays.asList(6, 5, 4, 3, 2, 1)) {
            if (frequencies.get(i) >= count) {
                return i * count;
            }
        }
        return 0;
    }
}
