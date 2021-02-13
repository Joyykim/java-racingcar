package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import utils.RandomUtils;

public class Cars {

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = new ArrayList<>(cars);
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public static Cars from(String inputCarName) {
        String[] carNames = splitCarsName(inputCarName);
        validateDuplicatedName(carNames);

        return new Cars(
                Arrays.stream(carNames)
                        .map(Car::from)
                        .collect(Collectors.toList())
        );
    }

    private static void validateDuplicatedName(String[] carNames) {
        Set<String> set = new HashSet<>(Arrays.asList(carNames));
        if (set.size() != carNames.length) {
            throw new RuntimeException();
        }
    }

    private static String[] splitCarsName(String carsName) {
        return carsName.split(",", -1);
    }

    public void driveAll() {
        for (Car car : cars) {
            car.drive(RandomUtils.nextInt(0, 9));
        }
    }

    public List<Car> getWinners() {
        int maxPosition = getMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private int getMaxPosition() {
        return cars.stream()
                .max(Comparator.comparingInt(Car::getPosition)).get().getPosition();
    }
}
