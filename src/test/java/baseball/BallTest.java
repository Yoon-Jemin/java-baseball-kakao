package baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BallTest {

    @Test
    @DisplayName("유효한 범위(1~9)의 숫자로 볼을 생성할 수 있다")
    void success() {
        Ball ball = new Ball(6, 0);

        Assertions.assertThat(ball).isEqualTo(new Ball(6, 0));
    }

    @Test
    @DisplayName("경계값 1로 볼을 생성할 수 있다")
    void success_lowerBoundary() {
        Assertions.assertThatNoException().isThrownBy(() -> new Ball(1, 0));
    }

    @Test
    @DisplayName("경계값 9로 볼을 생성할 수 있다")
    void success_upperBoundary() {
        Assertions.assertThatNoException().isThrownBy(() -> new Ball(9, 0));
    }

    @Test
    @DisplayName("경계값 0이 입력되면 예외를 발생시킨다")
    void fail_lowerBoundaryException() {
        Assertions.assertThatThrownBy(() -> new Ball(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Ball.RANGE_ERROR);
    }

    @Test
    @DisplayName("경계값 10이 입력되면 예외를 발생시킨다")
    void fail_upperBoundaryException() {
        Assertions.assertThatThrownBy(() -> new Ball(10, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Ball.RANGE_ERROR);
    }
}
