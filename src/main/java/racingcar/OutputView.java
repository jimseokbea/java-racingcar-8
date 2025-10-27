package racingcar;

import java.util.List;

public class OutputView {
    private static final String POSITION_MARK = "-";
    private static final String NAME_POSITION_DELIMITER = " : ";
    private static final String WINNER_DELIMITER = ", ";

    public static void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            printCarStatus(car);
        }
        System.out.println();
    }

    private static void printCarStatus(Car car) {
        System.out.println(car.getName() + NAME_POSITION_DELIMITER +
                createPositionMarks(car.getPosition()));
    }

    private static String createPositionMarks(int position) {
        StringBuilder marks = new StringBuilder();
        for (int i = 0; i < position; i++) {
            marks.append(POSITION_MARK);
        }
        return marks.toString();
    }

    public static void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(WINNER_DELIMITER, winners));
    }

    public static void printResultHeader() {
        System.out.println();
        System.out.println("실행 결과");
    }
}