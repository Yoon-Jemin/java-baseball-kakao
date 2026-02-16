import baseball.GameController;
import baseball.RandomNumberGenerator;
import baseball.view.TerminalInputView;
import baseball.view.TerminalOutputView;

public class Main {

    public static void main(String[] args) {
        GameController gameController = new GameController(
                new TerminalInputView(),
                new TerminalOutputView(),
                new RandomNumberGenerator()
        );

        gameController.start();
    }
}