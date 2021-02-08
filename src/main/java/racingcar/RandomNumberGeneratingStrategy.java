package racingcar;

import static racingcar.Rule.DRIVE_MAXIMUM_VALUE;
import static racingcar.Rule.DRIVE_MINIMUM_VALUE;

import utils.RandomUtils;

public class RandomNumberGeneratingStrategy implements NumberGeneratingStrategy {

    @Override
    public int generateNumber() {
        return RandomUtils.nextInt(DRIVE_MINIMUM_VALUE, DRIVE_MAXIMUM_VALUE);
    }
}
