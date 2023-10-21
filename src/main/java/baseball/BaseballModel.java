package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseballModel {

    private static final int NUMBERS_SIZE = 3;
    private static final int START_NUMBER = 1;
    private static final int END_NUMBER = 9;

    private final List<Integer> numbers = new ArrayList<>();

    public void chooseRandomNumbers() {
        while (numbers.size() < NUMBERS_SIZE) {
            int randomNumber = Randoms.pickNumberInRange(START_NUMBER, END_NUMBER);
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
    }

    public HintResult getHint(String input) {
        HintResult hintResult = new HintResult();
        List<Integer> inputNumbers = convertInputToList(input);
        for (int i = 0; i < NUMBERS_SIZE; i++) {
            updateBallAndStrike(inputNumbers, i, hintResult);
        }
        return hintResult;
    }

    private List<Integer> convertInputToList(String input) {
        return Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .toList();
    }

    private void updateBallAndStrike(List<Integer> inputNumbers, int index, HintResult hintResult) {
        int inputNumber = inputNumbers.get(index);
        if (numbers.contains(inputNumber)) {
            if (numbers.get(index).equals(inputNumber)) {
                hintResult.increaseStrike();
                return;
            }
            hintResult.increaseBall();
        }
    }
}
