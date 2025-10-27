package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RacingGameTest {

    @Test
    @DisplayName("레이싱 게임이 정상적으로 생성된다")
    void createRacingGame() {
        // given
        String[] carNames = {"pobi", "woni", "jun"};
        int attempts = 5;

        // when & then
        assertThatCode(() -> new RacingGame(carNames, attempts))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("시도 횟수가 0이면 예외가 발생한다")
    void createRacingGameWithZeroAttempts() {
        // given
        String[] carNames = {"pobi", "woni"};
        int attempts = 0;

        // when & then
        assertThatThrownBy(() -> new RacingGame(carNames, attempts))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("자동차 이름이 잘못되면 예외가 발생한다")
    void createRacingGameWithInvalidCarNames() {
        // given
        String[] carNames = {"pobi", "woni123"};
        int attempts = 5;

        // when & then
        assertThatThrownBy(() -> new RacingGame(carNames, attempts))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임이 정상적으로 실행된다")
    void playGame() {
        // given
        String[] carNames = {"pobi", "woni"};
        int attempts = 5;
        RacingGame game = new RacingGame(carNames, attempts);

        // when & then
        assertThatCode(() -> game.play())
                .doesNotThrowAnyException();
    }
}