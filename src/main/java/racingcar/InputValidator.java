package racingcar;

public class InputValidator {
    private static final int MAX_NAME_LENGTH = 5;

    public static void validateCarNames(String[] names) {
        if (names == null || names.length == 0) {
            throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
        }

        for (String name : names) {
            validateCarName(name);
        }
    }

    private static void validateCarName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어있을 수 없습니다.");
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "자동차 이름은 " + MAX_NAME_LENGTH + "자 이하여야 합니다."
            );
        }
    }

    public static void validateAttempts(int attempts) {
        if (attempts <= 0) {
            throw new IllegalArgumentException("시도 횟수는 양수여야 합니다.");
        }
    }
}