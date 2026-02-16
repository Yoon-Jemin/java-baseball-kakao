package baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BallsTest {

    private Balls answer;

    @BeforeEach
    void beforeEach() {
        answer = new Balls("123");
    }

    @Test
    @DisplayName("리스트로 3개의 볼을 생성할 수 있다")
    void success_listInput() {
        Balls balls = new Balls(List.of(1, 2, 3));

        Assertions.assertThat(balls).isEqualTo(new Balls(List.of(1, 2, 3)));
    }

    @Test
    @DisplayName("문자열로 3개의 볼을 생성할 수 있다")
    void success_stringInput() {
        Balls balls = new Balls("123");

        Assertions.assertThat(balls).isEqualTo(new Balls(List.of(1, 2, 3)));
    }

    @Test
    @DisplayName("볼의 개수가 3개가 아니면 예외를 발생시킨다")
    void fail_numberCountException() {
        Assertions.assertThatThrownBy(() -> new Balls(List.of(1, 2, 3, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Balls.BALL_COUNT_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("중복된 숫자가 있으면 예외를 발생시킨다")
    void fail_numberDuplicateException() {
        Assertions.assertThatThrownBy(() -> new Balls(List.of(1, 1, 2)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Balls.BALL_DUPLICATE_EXCEPTION_MESSAGE);
    }

    @Test
    @DisplayName("볼이 하나도 맞지 않으면 낫싱을 반환한다")
    void compareWith_nothing() {
        Balls guess = new Balls("456");
        GameResult gameResult = answer.compareWith(guess);

        Assertions.assertThat(gameResult).isEqualTo(new GameResult(0, 0));
    }

    @Test
    @DisplayName("같은 자리 1개, 다른 자리 1개가 맞으면 1스트라이크 1볼을 반환한다")
    void compareWith_strike1_ball1() {
        Balls guess = new Balls("135");
        GameResult gameResult = answer.compareWith(guess);

        Assertions.assertThat(gameResult).isEqualTo(new GameResult(1, 1));
    }

    @Test
    @DisplayName("같은 자리 2개가 맞으면 2스트라이크를 반환한다")
    void compareWith_strike2() {
        Balls guess = new Balls("125");
        GameResult gameResult = answer.compareWith(guess);

        Assertions.assertThat(gameResult).isEqualTo(new GameResult(2, 0));
    }

    @Test
    @DisplayName("모든 숫자가 같은 자리에서 맞으면 3스트라이크를 반환한다")
    void compareWith_strike3() {
        Balls guess = new Balls("123");
        GameResult gameResult = answer.compareWith(guess);

        Assertions.assertThat(gameResult).isEqualTo(new GameResult(3, 0));
    }
}
