package baseball.view;

import baseball.GameResult;

public interface OutputView {

    void printNumberInputMessage();

    void printResultMessage(GameResult gameResult);

    void printGameOverMessage();

    void printGameRestartMessage();

    void printErrorMessage(String message);
}
