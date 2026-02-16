package baseball;

import baseball.view.InputView;
import baseball.view.OutputView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameControllerTest {

    @Test
    @DisplayName("정상 입력 시 결과를 출력하고 종료한다")
    void success() {
        MockInputView inputView = new MockInputView(List.of(
                "123", "145", "671", "216", "713", "2"
        ));
        MockOutputView outputView = new MockOutputView();
        GameController controller = new GameController(inputView, outputView, new FixedNumberGenerator());

        controller.start();

        Assertions.assertThat(outputView.getOutputs()).containsExactly(
                "숫자를 입력해주세요 : ",
                "1스트라이크 1볼",
                "숫자를 입력해주세요 : ",
                "1볼",
                "숫자를 입력해주세요 : ",
                "2볼",
                "숫자를 입력해주세요 : ",
                "1스트라이크",
                "숫자를 입력해주세요 : ",
                "3스트라이크",
                "3개의 숫자를 모두 맞히셨습니다! 게임 끝",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
        );
    }

    @Test
    @DisplayName("잘못된 입력 시 에러 메시지를 출력하고 재입력을 받는다")
    void fail_invalidInput() {
        MockInputView inputView = new MockInputView(List.of(
                "112", "713", "2"
        ));
        MockOutputView outputView = new MockOutputView();
        GameController controller = new GameController(inputView, outputView, new FixedNumberGenerator());

        controller.start();

        Assertions.assertThat(outputView.getOutputs()).containsExactly(
                "숫자를 입력해주세요 : ",
                Balls.BALL_DUPLICATE_EXCEPTION_MESSAGE,
                "숫자를 입력해주세요 : ",
                "3스트라이크",
                "3개의 숫자를 모두 맞히셨습니다! 게임 끝",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
        );
    }

    @Test
    @DisplayName("게임 종료 후 1을 입력하면 새 게임을 시작한다")
    void restart() {
        MockInputView inputView = new MockInputView(List.of(
                "713", "1", "713", "2"
        ));
        MockOutputView outputView = new MockOutputView();
        GameController controller = new GameController(inputView, outputView, new FixedNumberGenerator());

        controller.start();

        Assertions.assertThat(outputView.getOutputs()).containsExactly(
                "숫자를 입력해주세요 : ",
                "3스트라이크",
                "3개의 숫자를 모두 맞히셨습니다! 게임 끝",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.",
                "숫자를 입력해주세요 : ",
                "3스트라이크",
                "3개의 숫자를 모두 맞히셨습니다! 게임 끝",
                "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요."
        );
    }

    static class FixedNumberGenerator implements NumberGenerator {
        @Override
        public List<Integer> generate() {
            return List.of(7, 1, 3);
        }
    }

    static class MockInputView implements InputView {

        private final Queue<String> queue;

        public MockInputView(List<String> inputs) {
            this.queue = new LinkedList<>(inputs);
        }

        @Override
        public String inputGuessNumber() {
            return queue.poll();
        }

        @Override
        public String inputRestartNumber() {
            return queue.poll();
        }
    }

    static class MockOutputView implements OutputView {

        private final List<String> outputs = new ArrayList<>();

        public List<String> getOutputs() {
            return outputs;
        }

        @Override
        public void printNumberInputMessage() {
            outputs.add("숫자를 입력해주세요 : ");
        }

        @Override
        public void printResultMessage(GameResult gameResult) {
            if (gameResult.isNothing()) {
                outputs.add("낫싱");
                return;
            }

            if (gameResult.hasOnlyStrike()) {
                outputs.add(gameResult.getStrike() + "스트라이크");
                return;
            }

            if (gameResult.hasOnlyBall()) {
                outputs.add(gameResult.getBall() + "볼");
                return;
            }

            outputs.add(gameResult.getStrike() + "스트라이크 " + gameResult.getBall() + "볼");
        }

        @Override
        public void printGameOverMessage() {
            outputs.add("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
        }

        @Override
        public void printGameRestartMessage() {
            outputs.add("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        }

        @Override
        public void printErrorMessage(String message) {
            outputs.add(message);
        }
    }
}
