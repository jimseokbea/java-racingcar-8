package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private static final int FORWARD_THRESHOLD = 4;
    private static final int MIN_RANDOM = 0;
    private static final int MAX_RANDOM = 9;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;
    private int position;

    public Car(String name) {
        validateName(name);
        this.name = name;
        this.position = 0;
    }

    private void validateName(String name) {
        validateNotEmpty(name);
        validateLength(name);
    }

    private void validateNotEmpty(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("이름은 비어있을 수 없습니다.");
        }
    }

    private void validateLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 5자 이하여야 합니다.");
        }
    }

    public void move() {
        if (canMove()) {
            position++;
        }
    }

    private boolean canMove() {
        int randomValue = generateRandomValue();
        return randomValue >= FORWARD_THRESHOLD;
    }

    private int generateRandomValue() {
        return Randoms.pickNumberInRange(MIN_RANDOM, MAX_RANDOM);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}