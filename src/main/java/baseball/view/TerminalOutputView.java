package baseball.view;

import baseball.GameResult;

public class TerminalOutputView implements OutputView {

    @Override
    public void printNumberInputMessage() {
        System.out.println("숫자를 입력해주세요 : ");
    }

    @Override
    public void printResultMessage(GameResult gameResult) {
        if (gameResult.getStrike() == 0 && gameResult.getBall() == 0) {
            System.out.println("낫싱");
            return;
        }

        if (gameResult.getBall() == 0) {
            System.out.println(gameResult.getStrike() + "스트라이크");
            return;
        }

        if (gameResult.getStrike() == 0) {
            System.out.println(gameResult.getBall() + "볼");
            return;
        }

        System.out.println(gameResult.getStrike() + "스트라이크 " + gameResult.getBall() + "볼");
    }

    @Override
    public void printGameOverMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
    }

    @Override
    public void printGameRestartMessage() {
        System.out.println();
    }

    @Override
    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
