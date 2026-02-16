package baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    @Test
    @DisplayName("3스트라이크이면 게임이 종료된다")
    void isGameOver_true() {
        GameResult result = new GameResult(3, 0);

        Assertions.assertThat(result.isGameOver()).isTrue();
    }

    @Test
    @DisplayName("3스트라이크가 아니면 게임이 종료되지 않는다")
    void isGameOver_false() {
        GameResult result = new GameResult(2, 0);

        Assertions.assertThat(result.isGameOver()).isFalse();
    }

    @Test
    @DisplayName("0스트라이크 0볼이면 낫싱이다")
    void isNothing_true() {
        GameResult result = new GameResult(0, 0);

        Assertions.assertThat(result.isNothing()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 또는 볼이 있으면 낫싱이 아니다")
    void isNothing_false() {
        GameResult result = new GameResult(1, 0);

        Assertions.assertThat(result.isNothing()).isFalse();
    }

    @Test
    @DisplayName("볼이 0개이면 스트라이크만 있다")
    void hasOnlyStrike_true() {
        GameResult result = new GameResult(1, 0);

        Assertions.assertThat(result.hasOnlyStrike()).isTrue();
    }

    @Test
    @DisplayName("볼이 1개 이상이면 스트라이크만 있지 않다")
    void hasOnlyStrike_false() {
        GameResult result = new GameResult(1, 1);

        Assertions.assertThat(result.hasOnlyStrike()).isFalse();
    }

    @Test
    @DisplayName("스트라이크가 0개이면 볼만 있다")
    void hasOnlyBall_true() {
        GameResult result = new GameResult(0, 1);

        Assertions.assertThat(result.hasOnlyBall()).isTrue();
    }

    @Test
    @DisplayName("스트라이크가 1개 이상이면 볼만 있지 않다")
    void hasOnlyBall_false() {
        GameResult result = new GameResult(1, 1);

        Assertions.assertThat(result.hasOnlyBall()).isFalse();
    }
}
