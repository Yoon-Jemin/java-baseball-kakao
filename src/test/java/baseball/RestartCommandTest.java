package baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RestartCommandTest {

    @Test
    @DisplayName("1을 입력하면 재시작 커맨드를 반환한다")
    void from_restart() {
        RestartCommand command = RestartCommand.from(1);

        Assertions.assertThat(command.isRestart()).isTrue();
    }

    @Test
    @DisplayName("2를 입력하면 종료 커맨드를 반환한다")
    void from_exit() {
        RestartCommand command = RestartCommand.from(2);

        Assertions.assertThat(command.isRestart()).isFalse();
    }

    @Test
    @DisplayName("1 또는 2가 아닌 값이 입력되면 예외를 발생시킨다")
    void from_invalidValue() {
        Assertions.assertThatThrownBy(() -> RestartCommand.from(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(RestartCommand.INVALID_COMMAND_MESSAGE);
    }
}
