package racingcar;

public class Car {
    private static final int FORWARD_THRESHOLD = 4;
    private static final int MIN_RANDOM = 0;
    private static final int MAX_RANDOM = 9;

    private final String name;
    private int position;

    public Car(String name) {
        this.name = name;
        this.position = 0;
    }

    public void move() {
        int randomValue = generateRandomValue();
        if (randomValue >= FORWARD_THRESHOLD) {
            position++;
        }
    }

    private int generateRandomValue() {
        return camp.nextstep.edu.missionutils.Randoms
                .pickNumberInRange(MIN_RANDOM, MAX_RANDOM);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}