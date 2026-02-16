package baseball;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Balls {

    public static final String BALL_COUNT_EXCEPTION_MESSAGE = "[ERROR] 입력된 볼의 개수가 3이 아닙니다.";
    public static final String BALL_DUPLICATE_EXCEPTION_MESSAGE = "[ERROR] 중복된 볼의 입력이 있습니다.";

    private final Set<Ball> balls;

    public Balls(List<Integer> ballNumbers) {
        validate(ballNumbers);
        validateDuplicate(ballNumbers);
        this.balls = IntStream.range(0, 3)
                .mapToObj(i -> new Ball(ballNumbers.get(i), i))
                .collect(Collectors.toSet());
    }

    public Balls(String guessNumber) {
        this(guessNumber.chars()
                .map(Character::getNumericValue)
                .boxed()
                .toList());
    }

    private static void validate(List<Integer> ballNumbers) {
        if (ballNumbers.size() != 3) {
            throw new IllegalArgumentException(BALL_COUNT_EXCEPTION_MESSAGE);
        }
    }

    private void validateDuplicate(List<Integer> ballNumbers) {
        Set<Integer> uniqueValues = new HashSet<>(ballNumbers);
        if (uniqueValues.size() != 3) {
            throw new IllegalArgumentException(BALL_DUPLICATE_EXCEPTION_MESSAGE);
        }
    }

    public GameResult calculate(Balls guess) {
        int strike = calculateStrike(guess);
        int ball = calculateBalls(guess);

        return new GameResult(strike, ball - strike);
    }

    private int calculateBalls(Balls guess) {
        Set<Integer> myValues = this.balls.stream()
                .map(Ball::getValue)
                .collect(Collectors.toSet());

        return Math.toIntExact(guess.balls.stream()
                .map(Ball::getValue)
                .filter(myValues::contains)
                .count());
    }

    private int calculateStrike(Balls guess) {
        Set<Ball> intersection = new HashSet<>(guess.balls);
        intersection.retainAll(this.balls);
        return intersection.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Balls balls1 = (Balls) o;
        return Objects.equals(balls, balls1.balls);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(balls);
    }
}
