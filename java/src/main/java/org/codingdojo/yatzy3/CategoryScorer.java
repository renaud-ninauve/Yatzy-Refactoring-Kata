package org.codingdojo.yatzy3;

import org.codingdojo.YatzyCategory;

import java.util.List;

public abstract class CategoryScorer {
    public static CategoryScorer createInstance(String categoryName) {
        YatzyCategory category = YatzyCategory.valueOf(categoryName);
        MathsUtils mathsUtils = new MathsUtils();
        return switch (category) {
            case CHANCE -> new ChanceScorer();
            case YATZY -> new YatzyPointsScorer(mathsUtils);
            case ONES -> new NumberScorer(mathsUtils, 1);
            case TWOS -> new NumberScorer(mathsUtils, 2);
            case THREES -> new NumberScorer(mathsUtils, 3);
            case FOURS -> new NumberScorer(mathsUtils, 4);
            case FIVES -> new NumberScorer(mathsUtils, 5);
            case SIXES -> new NumberScorer(mathsUtils, 6);
            case PAIR -> new RepeatedCountScorer(mathsUtils, 2);
            case THREE_OF_A_KIND -> new RepeatedCountScorer(mathsUtils, 3);
            case FOUR_OF_A_KIND -> new RepeatedCountScorer(mathsUtils, 4);
            case SMALL_STRAIGHT -> new StraightScorer(mathsUtils, 1);
            case LARGE_STRAIGHT -> new StraightScorer(mathsUtils, 6);
            case TWO_PAIRS -> new TwoPairsScorer(mathsUtils);
            case FULL_HOUSE -> new FullHouseScorer(mathsUtils);
        };
    }

    public abstract int calculateScore(List<Integer> dice);


    int sum(List<Integer> dice) {
        return dice.stream().mapToInt(Integer::intValue).sum();
    }

}
