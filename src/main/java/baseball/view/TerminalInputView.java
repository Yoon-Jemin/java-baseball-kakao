package baseball.view;

import java.util.Scanner;

public class TerminalInputView implements InputView {

    private Scanner scanner;

    public TerminalInputView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String inputGuessNumber() {
        return this.scanner.nextLine();
    }

    @Override
    public String inputRestartNumber() {
        return this.scanner.nextLine();
    }
}
