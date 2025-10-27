package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class CarTest {

    @Test
    @DisplayName("자동차 생성 시 이름과 초기 위치가 올바르게 설정된다")
    void createCar() {
        // given
        String name = "pobi";

        // when
        Car car = new Car(name);

        // then
        assertThat(car.getName()).isEqualTo("pobi");
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    @DisplayName("자동차 이름이 5자 이하일 때 정상적으로 생성된다")
    void createCarWithValidName() {
        // given
        String name = "pobi";

        // when & then
        assertThatCode(() -> new Car(name))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    void createCarWithLongName() {
        // given
        String name = "pobi123";

        // when & then
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 이하");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("자동차 이름이 비어있으면 예외가 발생한다")
    void createCarWithEmptyName(String name) {
        assertThatThrownBy(() -> new Car(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어있을 수 없습니다");
    }

    @Test
    @DisplayName("자동차 이름이 null이면 예외가 발생한다")
    void createCarWithNullName() {
        assertThatThrownBy(() -> new Car(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어있을 수 없습니다");
    }

    @Test
    @DisplayName("자동차가 전진하면 위치가 1 증가한다")
    void moveCarForward() {
        // given
        Car car = new Car("pobi");
        int initialPosition = car.getPosition();

        // when
        // 여러 번 실행하면 적어도 한 번은 전진할 것
        for (int i = 0; i < 100; i++) {
            car.move();
        }

        // then
        assertThat(car.getPosition()).isGreaterThan(initialPosition);
    }

    @Test
    @DisplayName("자동차의 위치는 음수가 될 수 없다")
    void carPositionIsNeverNegative() {
        // given
        Car car = new Car("pobi");

        // when
        for (int i = 0; i < 10; i++) {
            car.move();
        }

        // then
        assertThat(car.getPosition()).isGreaterThanOrEqualTo(0);
    }
}