package org.codingdojo.yatzy2;

import org.codingdojo.YatzyCalculator;
import org.codingdojo.YatzyCategory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Yatzy2 implements YatzyCalculator {
    static final List<Integer> DICE_VALUES = Arrays.asList(6, 5, 4, 3, 2, 1);


    @Override
    public List<String> validCategories() {
        return Arrays.stream(YatzyCategory.values()).map(Enum::toString).collect(Collectors.toList());
    }

    @Override
    public int score(List<Integer> dice, String categoryName) {
        YatzyCategory category = YatzyCategory.valueOf(categoryName);
        Map<Integer, Integer> diceFrequencies = diceFrequencies(dice);
        return score(dice, category, diceFrequencies);
    }

    private int score(List<Integer> dice, YatzyCategory category, Map<Integer, Integer> diceFrequencies) {
        return switch (category) {
            case CHANCE -> scoreChance(dice);
            case YATZY -> scoreYatzy(diceFrequencies);
            case ONES -> scoreNs(diceFrequencies, 1);
            case TWOS -> scoreNs(diceFrequencies, 2);
            case THREES -> scoreNs(diceFrequencies, 3);
            case FOURS -> scoreNs(diceFrequencies, 4);
            case FIVES -> scoreNs(diceFrequencies, 5);
            case SIXES -> scoreNs(diceFrequencies, 6);
            case PAIR -> scoreNthOfAKind(diceFrequencies, 2);
            case THREE_OF_A_KIND -> scoreNthOfAKind(diceFrequencies, 3);
            case FOUR_OF_A_KIND -> scoreNthOfAKind(diceFrequencies, 4);
            case SMALL_STRAIGHT -> scoreSmallStraight(dice, diceFrequencies);
            case LARGE_STRAIGHT -> scoreLargeStraight(dice, diceFrequencies);
            case TWO_PAIRS -> scoreTwoPairs(diceFrequencies);
            case FULL_HOUSE -> scoreFullHouse(dice, diceFrequencies);
        };
    }

    private static int scoreFullHouse(List<Integer> dice, Map<Integer, Integer> diceFrequencies) {
        return diceFrequencies.containsValue(2) && diceFrequencies.containsValue(3)
            ? scoreChance(dice) : 0;
    }

    private static int scoreTwoPairs(Map<Integer, Integer> diceFrequencies) {
        int result;
        int twoPairResult = 0;
        long pairCount = 0L;
        for (Integer frequency : diceFrequencies.values()) {
            if (frequency >= 2) {
                pairCount++;
            }
        }
        if (pairCount == 2) {
            for (int i : DICE_VALUES) {
                if (diceFrequencies.get(i) >= 2) {
                    twoPairResult += i * 2;
                }
            }
        }
        result = twoPairResult;
        return result;
    }

    private static int scoreLargeStraight(List<Integer> dice, Map<Integer, Integer> diceFrequencies) {
        int result;
        int largeStraightResult = 0;
        long straightCount = 0L;
        for (Integer frequency : diceFrequencies.values()) {
            if (frequency == 1) {
                straightCount++;
            }
        }
        if (straightCount == 5 && diceFrequencies.get(1) == 0) {
            for (Integer die : dice) {
                largeStraightResult += die;
            }
        }
        result = largeStraightResult;
        return result;
    }

    private static int scoreSmallStraight(List<Integer> dice, Map<Integer, Integer> diceFrequencies) {
        int result;
        int smallStraightResult = 0;
        long count = 0L;
        for (Integer frequency : diceFrequencies.values()) {
            if (frequency == 1) {
                count++;
            }
        }
        if (count == 5 && diceFrequencies.get(6) == 0) {
            for (Integer die : dice) {
                smallStraightResult += die;
            }
        }
        result = smallStraightResult;
        return result;
    }

    private static int scoreNthOfAKind(Map<Integer, Integer> diceFrequencies, int n) {
        int result;
        int fourKindResult = 0;
        for (int i : DICE_VALUES) {
            if (diceFrequencies.get(i) >= n) {
                fourKindResult = i * n;
                break;
            }
        }
        result = fourKindResult;
        return result;
    }

    private static int scoreNs(Map<Integer, Integer> diceFrequencies, int n) {
        int result;
        result = diceFrequencies.get(n) * n;
        return result;
    }

    private static int scoreYatzy(Map<Integer, Integer> diceFrequencies) {
        int result;
        int yatzyResult = 0;
        if (diceFrequencies.containsValue(5)) {
            yatzyResult = 50;
        }
        result = yatzyResult;
        return result;
    }

    private static int scoreChance(List<Integer> dice) {
        int result;
        result = dice.stream().mapToInt(Integer::intValue).sum();
        return result;
    }

    private static Map<Integer, Integer> diceFrequencies(List<Integer> dice) {
        Map<Integer, Integer> diceFrequencies = new HashMap<>();
        for (int i : DICE_VALUES) {
            diceFrequencies.put(i, 0);
        }
        for (int die : dice) {
            diceFrequencies.put(die, diceFrequencies.get(die) + 1);
        }
        return diceFrequencies;
    }

}

