package baseball;

import java.util.Objects;

public class Ball {

    public static final String RANGE_ERROR = "[ERROR] 1 ~ 9 이외의 숫자가 입력되었습니다.";

    private final int value;
    private final int position;

    public Ball(int value, int position) {
        validateRange(value);
        this.value = value;
        this.position = position;
    }

    private void validateRange(int value) {
        if (value < 1 || value > 9) {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return value == ball.value && position == ball.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, position);
    }
}
