package racingcar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class OutputViewTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("라운드 결과가 올바른 형식으로 출력된다")
    void printRoundResult() {
        // given
        Car car1 = new Car("pobi");
        Car car2 = new Car("woni");
        List<Car> cars = Arrays.asList(car1, car2);

        // when
        OutputView.printRoundResult(cars);
        String output = outputStream.toString();

        // then
        assertThat(output).contains("pobi : ");
        assertThat(output).contains("woni : ");
    }

    @Test
    @DisplayName("자동차 위치가 - 문자로 표시된다")
    void printCarPosition() {
        // given
        Car car = new Car("pobi");
        // 강제로 위치 증가시키기 (여러 번 move 호출)
        for (int i = 0; i < 10; i++) {
            car.move();
        }
        List<Car> cars = Arrays.asList(car);

        // when
        OutputView.printRoundResult(cars);
        String output = outputStream.toString();

        // then
        assertThat(output).contains("pobi : ");
        // 위치가 0보다 크면 - 가 있어야 함
        if (car.getPosition() > 0) {
            assertThat(output).contains("-");
        }
    }

    @Test
    @DisplayName("우승자가 올바른 형식으로 출력된다")
    void printWinners() {
        // given
        List<String> winners = Arrays.asList("pobi");

        // when
        OutputView.printWinners(winners);
        String output = outputStream.toString();

        // then
        assertThat(output).contains("최종 우승자 : pobi");
    }

    @Test
    @DisplayName("공동 우승자가 쉼표로 구분되어 출력된다")
    void printMultipleWinners() {
        // given
        List<String> winners = Arrays.asList("pobi", "jun");

        // when
        OutputView.printWinners(winners);
        String output = outputStream.toString();

        // then
        assertThat(output).contains("최종 우승자 : ");
        assertThat(output).contains("pobi");
        assertThat(output).contains("jun");
        assertThat(output).contains(", ");
    }

    @Test
    @DisplayName("실행 결과 헤더가 출력된다")
    void printResultHeader() {
        // when
        OutputView.printResultHeader();
        String output = outputStream.toString();

        // then
        assertThat(output).contains("실행 결과");
    }
}