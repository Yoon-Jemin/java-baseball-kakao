package baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BallTest {

    @Test
    @DisplayName("예측하는 숫자를 입력할 수 있다.")
    void success() {
        Ball ball = new Ball(6, 0);

        Assertions.assertThat(ball).isEqualTo(new Ball(6, 0));
    }

    @Test
    @DisplayName("1 ~ 9 이외의 숫자가 입력이 되면 예외를 발생시킨다.")
    void fail_rangeException() {
        Assertions.assertThatThrownBy(() -> {
            Ball ball = new Ball(0, 0);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Ball.RANGE_ERROR);
    }
}
