package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CarsTest {

    @Test
    @DisplayName("자동차들이 올바르게 생성된다")
    void createCars() {
        // given
        String[] names = {"pobi", "woni", "jun"};

        // when
        Cars cars = new Cars(names);

        // then
        assertThat(cars.getCars()).hasSize(3);
        assertThat(cars.getCars().get(0).getName()).isEqualTo("pobi");
        assertThat(cars.getCars().get(1).getName()).isEqualTo("woni");
        assertThat(cars.getCars().get(2).getName()).isEqualTo("jun");
    }

    @Test
    @DisplayName("모든 자동차가 이동을 시도한다")
    void moveAllCars() {
        // given
        String[] names = {"pobi", "woni", "jun"};
        Cars cars = new Cars(names);

        // when
        for (int i = 0; i < 100; i++) {
            cars.moveAll();
        }

        // then
        // 100번 시도하면 적어도 한 대는 움직일 것
        boolean anyCarMoved = cars.getCars().stream()
                .anyMatch(car -> car.getPosition() > 0);
        assertThat(anyCarMoved).isTrue();
    }

    @Test
    @DisplayName("최대 위치를 가진 자동차가 우승자로 선정된다")
    void findWinners() {
        // given
        String[] names = {"pobi", "woni", "jun"};
        Cars cars = new Cars(names);

        // when
        // 충분히 이동시켜 우승자가 나오도록 함
        for (int i = 0; i < 10; i++) {
            cars.moveAll();
        }
        List<String> winners = cars.findWinners();

        // then
        assertThat(winners).isNotEmpty();
        assertThat(winners.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("우승자는 최소 1명 이상이다")
    void winnersCountIsPositive() {
        // given
        String[] names = {"pobi", "woni"};
        Cars cars = new Cars(names);

        // when
        List<String> winners = cars.findWinners();

        // then
        assertThat(winners).hasSizeGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("모든 자동차가 같은 위치면 모두 우승자다")
    void allCarsWinWhenSamePosition() {
        // given
        String[] names = {"pobi", "woni", "jun"};
        Cars cars = new Cars(names);

        // when
        // 이동하지 않으면 모두 위치 0
        List<String> winners = cars.findWinners();

        // then
        assertThat(winners).hasSize(3);
        assertThat(winners).containsExactlyInAnyOrder("pobi", "woni", "jun");
    }
}