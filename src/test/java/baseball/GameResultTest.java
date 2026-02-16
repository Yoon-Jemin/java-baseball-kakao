package baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    private Balls answer;

    @BeforeEach
    void beforeEach() {
        answer = new Balls("123");
    }

    @Test
    @DisplayName("낫싱 테스트")
    void nothing() {
        Balls guess = new Balls("456");
        GameResult gameResult = answer.calculate(guess);

        Assertions.assertThat(gameResult).isEqualTo(new GameResult(0, 0));
    }

    @Test
    @DisplayName("스트라이크 1 볼 1")
    void strike_1_ball_1() {
        Balls guess = new Balls("135");
        GameResult gameResult = answer.calculate(guess);

        Assertions.assertThat(gameResult).isEqualTo(new GameResult(1, 1));
    }

    @Test
    @DisplayName("스트라이크 2")
    void strike_2() {
        Balls guess = new Balls("125");
        GameResult gameResult = answer.calculate(guess);

        Assertions.assertThat(gameResult).isEqualTo(new GameResult(2, 0));
    }

    @Test
    @DisplayName("스트라이크 3")
    void strike_3() {
        Balls guess = new Balls("123");
        GameResult gameResult = answer.calculate(guess);

        Assertions.assertThat(gameResult).isEqualTo(new GameResult(3, 0));
    }
}
