package racingcar;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(String[] names) {
        InputValidator.validateCarNames(names);
        this.cars = createCars(names);
    }

    private List<Car> createCars(String[] names) {
        List<Car> carList = new ArrayList<>();
        for (String name : names) {
            carList.add(new Car(name));
        }
        return carList;
    }

    public void moveAll() {
        for (Car car : cars) {
            car.move();
        }
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<String> findWinners() {
        int maxPosition = findMaxPosition();
        return findCarsByPosition(maxPosition);
    }

    private int findMaxPosition() {
        int max = 0;
        for (Car car : cars) {
            if (car.getPosition() > max) {
                max = car.getPosition();
            }
        }
        return max;
    }

    private List<String> findCarsByPosition(int position) {
        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition() == position) {
                winners.add(car.getName());
            }
        }
        return winners;
    }
}