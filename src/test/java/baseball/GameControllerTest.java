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
    @DisplayName("통합 테스트")
    void success() {
        NumberGenerator generator = new FixedNumberGenerator();
        MockInputView inputView = new MockInputView(List.of(
                "123",
                "145",
                "671",
                "216",
                "713",
                "2"
        ));
        MockOutputView outputView = new MockOutputView();

        GameController controller = new GameController(inputView, outputView, generator);

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

    static class FixedNumberGenerator implements NumberGenerator {
        @Override
        public List<Integer> generate() {
            return List.of(7, 1, 3);
        }
    }

    static class MockInputView implements InputView {

        private Queue<String> queue;

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

    private class MockOutputView implements OutputView {

        private List<String> outputs;

        public List<String> getOutputs() {
            return outputs;
        }

        public MockOutputView() {
            this.outputs = new ArrayList<>();
        }

        @Override
        public void printNumberInputMessage() {
            outputs.add("숫자를 입력해주세요 : ");
        }

        @Override
        public void printResultMessage(GameResult gameResult) {
            if (gameResult.getStrike() == 0 && gameResult.getBall() == 0) {
                outputs.add("낫싱");
                return;
            }

            if (gameResult.getBall() == 0) {
                outputs.add(gameResult.getStrike() + "스트라이크");
                return;
            }

            if (gameResult.getStrike() == 0) {
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
