package baseball;

import baseball.view.InputView;
import baseball.view.OutputView;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final NumberGenerator numberGenerator;

    public GameController(InputView inputView, OutputView outputView, NumberGenerator numberGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.numberGenerator = numberGenerator;
    }

    public void start() {
        Balls answerBalls = new Balls(numberGenerator.generate());

        while (true) {
            GameResult gameResult = play(answerBalls);
            if (gameResult.getStrike() == 3) {
                if (!keepGoing()) break;
                answerBalls = new Balls(numberGenerator.generate());
            }
        }
    }

    private GameResult play(Balls answerBalls) {
        while (true) {
            outputView.printNumberInputMessage();
            try {
                Balls guessBalls = new Balls(inputView.inputGuessNumber());
                GameResult gameResult = answerBalls.calculate(guessBalls);
                outputView.printResultMessage(gameResult);
                return gameResult;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean keepGoing() {
        outputView.printGameOverMessage();
        outputView.printGameRestartMessage();
        int restart = Integer.parseInt(inputView.inputRestartNumber());

        return restart == 1;
    }
}
