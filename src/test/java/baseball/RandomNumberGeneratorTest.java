package baseball;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RandomNumberGeneratorTest {

    @Test
    @DisplayName("1~9 범위의 중복 없는 랜덤 숫자 3자리를 생성한다")
    void success() {
        NumberGenerator generator = new RandomNumberGenerator();
        List<Integer> randomNumbers = generator.generate();

        Assertions.assertThat(randomNumbers)
                .hasSize(3)
                .allMatch(number -> number >= 1 && number <= 9)
                .doesNotHaveDuplicates();
    }
}
