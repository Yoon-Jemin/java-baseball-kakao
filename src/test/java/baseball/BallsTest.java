package baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BallsTest {

    @Test
    @DisplayName("정상적으로 3개의 숫자가 리스트로 입력된 경우")
    void success_listInput() {
        Balls balls = new Balls(List.of(1, 2, 3));

        Assertions.assertThat(balls).isEqualTo(new Balls(List.of(1, 2, 3)));
    }

    @Test
    @DisplayName("정상적으로 3개의 숫자가 정수형으로 입력된 경우")
    void success_stringInput() {
        Balls balls = new Balls("123");

        Assertions.assertThat(balls).isEqualTo(new Balls(List.of(1, 2, 3)));
    }

    @Test
    @DisplayName("입력된 숫자가 3개가 아닌 경우 예외를 발생시킨다")
    void fail_numberCountException() {
        Assertions.assertThatThrownBy(() -> {
            Balls balls = new Balls(List.of(1, 2, 3, 4));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Balls.BALL_COUNT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("입력된 숫자에 중복이 있으면 예외를 발생시킨다")
    void fail_numberDuplicateException() {
        Assertions.assertThatThrownBy(() -> {
                    Balls balls = new Balls(List.of(1, 1, 2));
                }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Balls.BALL_DUPLICATE_EXCEPTION_MESSAGE);
    }

}
