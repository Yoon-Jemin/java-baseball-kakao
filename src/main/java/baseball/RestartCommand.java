package baseball;

public enum RestartCommand {

    RESTART(1), EXIT(2);

    public static final String INVALID_COMMAND_MESSAGE = "[ERROR] 1 또는 2를 입력해주세요.";

    private final int value;

    RestartCommand(int value) {
        this.value = value;
    }

    public static RestartCommand from(int value) {
        for (RestartCommand command : values()) {
            if (command.value == value) {
                return command;
            }
        }
        throw new IllegalArgumentException(INVALID_COMMAND_MESSAGE);
    }

    public boolean isRestart() {
        return this == RESTART;
    }
}
