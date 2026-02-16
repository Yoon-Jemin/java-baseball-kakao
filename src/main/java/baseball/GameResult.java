package baseball;

import java.util.Objects;

public class GameResult {

    private final int strike;
    private final int ball;

    public GameResult(int strike, int ball) {
        this.strike = strike;
        this.ball = ball;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return strike == that.strike && ball == that.ball;
    }

    public int getBall() {
        return ball;
    }

    public int getStrike() {
        return strike;
    }

    public boolean isGameOver() {
        return strike == 3;
    }

    public boolean isNothing() {
        return strike == 0 && ball == 0;
    }

    public boolean hasOnlyStrike() {
        return ball == 0;
    }

    public boolean hasOnlyBall() {
        return strike == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(strike, ball);
    }
}
