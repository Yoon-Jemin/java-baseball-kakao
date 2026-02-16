package baseball.view;

import baseball.GameResult;

public class TerminalOutputView implements OutputView {

    @Override
    public void printNumberInputMessage() {
        System.out.print("숫자를 입력해주세요 : ");
    }

    @Override
    public void printResultMessage(GameResult gameResult) {
        if (gameResult.isNothing()) {
            System.out.println("낫싱");
            return;
        }

        if (gameResult.hasOnlyStrike()) {
            System.out.println(gameResult.getStrike() + "스트라이크");
            return;
        }

        if (gameResult.hasOnlyBall()) {
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
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

    @Override
    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
