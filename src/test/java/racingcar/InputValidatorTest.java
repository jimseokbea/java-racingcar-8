package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class InputValidatorTest {

    @Test
    @DisplayName("올바른 자동차 이름들은 검증을 통과한다")
    void validateValidCarNames() {
        // given
        String[] names = {"pobi", "woni", "jun"};

        // when & then
        assertThatCode(() -> InputValidator.validateCarNames(names))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("자동차 이름 배열이 null이면 예외가 발생한다")
    void validateNullCarNames() {
        assertThatThrownBy(() -> InputValidator.validateCarNames(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어있을 수 없습니다");
    }

    @Test
    @DisplayName("자동차 이름 배열이 비어있으면 예외가 발생한다")
    void validateEmptyCarNames() {
        // given
        String[] names = {};

        // when & then
        assertThatThrownBy(() -> InputValidator.validateCarNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어있을 수 없습니다");
    }

    @Test
    @DisplayName("자동차 이름이 5자를 초과하면 예외가 발생한다")
    void validateLongCarName() {
        // given
        String[] names = {"pobi", "woni123", "jun"};

        // when & then
        assertThatThrownBy(() -> InputValidator.validateCarNames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("5자 이하");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("자동차 이름이 빈 문자열이면 예외가 발생한다")
    void validateBlankCarName(String name) {
        // given
        String[] names = {"pobi", name, "jun"};

        // when & then
        assertThatThrownBy(() -> InputValidator.validateCarNames(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("양수 시도 횟수는 검증을 통과한다")
    void validatePositiveAttempts() {
        // given
        int attempts = 5;

        // when & then
        assertThatCode(() -> InputValidator.validateAttempts(attempts))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("시도 횟수가 0이면 예외가 발생한다")
    void validateZeroAttempts() {
        assertThatThrownBy(() -> InputValidator.validateAttempts(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수");
    }

    @Test
    @DisplayName("시도 횟수가 음수면 예외가 발생한다")
    void validateNegativeAttempts() {
        assertThatThrownBy(() -> InputValidator.validateAttempts(-1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 100})
    @DisplayName("다양한 양수 시도 횟수가 검증을 통과한다")
    void validateVariousPositiveAttempts(int attempts) {
        assertThatCode(() -> InputValidator.validateAttempts(attempts))
                .doesNotThrowAnyException();
    }
}